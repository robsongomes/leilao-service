package br.com.igti.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.igti.dto.ItemLoteDTO;
import br.com.igti.dto.LoteDTO;
import br.com.igti.dto.LoteRespostaDTO;
import br.com.igti.model.ItemLote;
import br.com.igti.model.Leilao;
import br.com.igti.model.Lote;
import br.com.igti.repository.LeilaoRepository;
import br.com.igti.repository.LoteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/lotes")
@Api(value = "Lotes API")
public class LoteController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LeilaoRepository leilaoRepository;
	
	@Autowired
	private LoteRepository repository;
	
	@GetMapping("/{id}")
	@ApiOperation(value = "API para consultar um Lote por id")
	private LoteRespostaDTO getById(@PathVariable Long id) {
		return modelMapper.map(repository.getOne(id), LoteRespostaDTO.class);		
	}	
	
	@PostMapping
	@ApiOperation(value = "API para criação de um Lote de Leilão")
	public LoteRespostaDTO createLote(@RequestBody LoteDTO loteDTO) {
		Leilao leilao = leilaoRepository.getOne(Long.parseLong(loteDTO.getLeilaoId()));
		Lote lote = modelMapper.map(loteDTO, Lote.class);
		lote.setLeilao(leilao);		
		repository.save(lote);
		return modelMapper.map(lote, LoteRespostaDTO.class);
	}
	
	@PostMapping("/{loteId}/item")
	@ApiOperation(value = "API para inclusão de um Item em um Lote de Leilão")
	public void adicionarItemLote(@RequestBody ItemLoteDTO itemLoteDTO, @PathVariable Long loteId) {
		System.out.println(itemLoteDTO);
		ItemLote item = modelMapper.map(itemLoteDTO, ItemLote.class);
		System.out.println(item);
	}
}
