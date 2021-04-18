package br.com.igti.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igti.dto.LeilaoDTO;
import br.com.igti.dto.LeilaoRespostaDTO;
import br.com.igti.dto.LeiloeiroDTO;
import br.com.igti.dto.LoteRespostaDTO;
import br.com.igti.dto.ParticipanteDTO;
import br.com.igti.dto.ParticipanteHabilitadoRespostaDTO;
import br.com.igti.model.Leilao;
import br.com.igti.model.Lote;
import br.com.igti.model.ParticipanteHabilitado;
import br.com.igti.repository.LeilaoRepository;
import br.com.igti.repository.LoteRepository;
import br.com.igti.repository.ParticipanteHabilitadoRepository;
import br.com.igti.service.LeiloeiroService;
import br.com.igti.service.ParticipanteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/leiloes")
@Api(value = "Leilões API")
public class LeilaoController {
	
	@Autowired
	private LeiloeiroService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LeilaoRepository repository;
	
	@Autowired
	private ParticipanteHabilitadoRepository participanteHabilitadoRepository;
	
	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@GetMapping
	@ApiOperation(value = "API para consultar os leilões ativos")
	public List<LeilaoRespostaDTO> list() {
		List<Leilao> all = repository.findByFechamentoLessThanEqual(new Date());
		return all.stream().map(l -> modelMapper.map(l, LeilaoRespostaDTO.class)).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "API para consulta de um leilão por id")
	public LeilaoRespostaDTO get(@PathVariable Long id) {
		Leilao leilao = repository.getOne(id);
		LeilaoRespostaDTO leilaoRespostaDTO = modelMapper.map(leilao, LeilaoRespostaDTO.class);
		//TODO: Resiliência: o que fazer caso o servço de leiloeiros esteja fora do ar ou não retornar os dados corretamente?
		leilaoRespostaDTO.setLeiloeiro(service.findLeiloeiroById(leilao.getIdLeiloeiro().toString()));
		return leilaoRespostaDTO;
	}
	
	@PostMapping
	@ApiOperation(value = "API para criação de um Leilão")
	public ResponseEntity<?> create(@RequestBody LeilaoDTO leilaoDTO) {
		Leilao leilao = modelMapper.map(leilaoDTO, Leilao.class);
		//TODO: Resiliência: o que fazer caso o servço de leiloeiros esteja fora do ar ou não retornar os dados corretamente?
		try {
			LeiloeiroDTO leiloeiro = service.findLeiloeiroByCnpj(leilaoDTO.getCnpjLeiloeiro());			
			leilao.setIdLeiloeiro(leiloeiro.getId());
			repository.save(leilao);
			return ResponseEntity.ok(modelMapper.map(leilao, LeilaoRespostaDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Serviço inoperante. Tente novamente mais tarde");
		}		
	}
	
	@PostMapping("/{idLeilao}/habilitacao/{cpf}")
	@ApiOperation(value = "API para habilitar um usuário em um Leilão")
	public ResponseEntity<?> habilitarUsuario(@PathVariable Long idLeilao, @PathVariable String cpf) {
		try {
			ParticipanteDTO participante = participanteService.findParticipanteByCpf(cpf);
			Leilao leilao = repository.getOne(idLeilao);
			if (!leilao.isLeilaoAbertoParaHabilitacao()) {
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Leilão não encontrado ou já encerrado.");
			}
			ParticipanteHabilitado habilitacao = new ParticipanteHabilitado();
			habilitacao.setIdParticipante(Long.parseLong(participante.getId()));
			habilitacao.setLeilao(leilao);
			habilitacao.setDataHabilitacao(new Date());
			participanteHabilitadoRepository.save(habilitacao);			
			return ResponseEntity.status(201).body(modelMapper.map(habilitacao, ParticipanteHabilitadoRespostaDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Serviço inoperante. Tente novamente mais tarde");
		}
	}
	
	@GetMapping("/{idLeilao}/habilitacao/{cpf}")
	@ApiOperation(value = "API para consultar um usuário habilitado para um leilão")
	public ResponseEntity<?> getUsuarioHabilitado(@PathVariable Long idLeilao, @PathVariable String cpf) {
		try {			
			ParticipanteDTO participante = participanteService.findParticipanteByCpf(cpf);
			ParticipanteHabilitado habilitacao = participanteHabilitadoRepository.findByIdParticipante(Long.parseLong(participante.getId()));						
			return ResponseEntity.status(201).body(modelMapper.map(habilitacao, ParticipanteHabilitadoRespostaDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Serviço inoperante. Tente novamente mais tarde");
		}
	}
	
	@GetMapping("/{idLeilao}/lotes")
	@ApiOperation(value = "API para consultar os Lotes de um Leilão")
	public List<LoteRespostaDTO> getLotesDeUmLeilao(@PathVariable Long idLeilao) {
		List<Lote> lotes = loteRepository.findByLeilaoId(idLeilao);
		return lotes.stream().map(lote -> modelMapper.map(lote, LoteRespostaDTO.class)).collect(Collectors.toList());
	}
}
