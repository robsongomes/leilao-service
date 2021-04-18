package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeilaoDTO {	
	
	private String titulo;
	private String descricao;
	private String cnpjLeiloeiro;
	private String abertura;
	private String fechamento;
	private String modalidade;
}
