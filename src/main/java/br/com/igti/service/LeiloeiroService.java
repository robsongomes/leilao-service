package br.com.igti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.igti.config.LeiloeirosEndpointConfig;
import br.com.igti.dto.LeiloeiroDTO;

@Service
public class LeiloeiroService {

	@Autowired
	LeiloeirosEndpointConfig destination;
	
	@Autowired
	RestTemplate client;
	
	public LeiloeiroDTO findLeiloeiroByCnpj(String cnpj) {
		return client.getForEntity(destination.getCnpjEndpoint() + "/{cnpj}", LeiloeiroDTO.class, cnpj).getBody();
	}
	
	public LeiloeiroDTO findLeiloeiroById(String id) {
		return client.getForEntity(destination.getEndpoint() + "/{id}", LeiloeiroDTO.class, id).getBody();
	}
	
	
}
