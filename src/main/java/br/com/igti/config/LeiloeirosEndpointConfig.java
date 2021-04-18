package br.com.igti.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "leiloeiros")
public class LeiloeirosEndpointConfig {	
	
	private String cnpjEndpoint;
	
	private String endpoint;
}
