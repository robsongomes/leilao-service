package br.com.igti.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "participantes")
@Data
public class ParticipantesEndepointConfig {

	private String endpoint;
	
	private String cpfEndpoint;
}
