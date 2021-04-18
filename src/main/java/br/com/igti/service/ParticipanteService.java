package br.com.igti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.igti.config.ParticipantesEndepointConfig;
import br.com.igti.dto.ParticipanteDTO;

@Service
public class ParticipanteService {

	@Autowired
	private ParticipantesEndepointConfig destination;
	
	@Autowired
	RestTemplate client;
	
	public ParticipanteDTO findParticipanteByCpf(String cpf) {
		return client.getForEntity(destination.getCpfEndpoint() + "/{cpf}", ParticipanteDTO.class, cpf).getBody();
	}
	
	public ParticipanteDTO findParticipanteById(String id) {
		return client.getForEntity(destination.getEndpoint() + "/{id}", ParticipanteDTO.class, id).getBody();
	}	
}
