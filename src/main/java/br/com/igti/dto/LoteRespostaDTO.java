package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoteRespostaDTO {

	private String id;
	private String descricao;
	private String precoInicial;
	private String leilaoId;
	private String leilaoTitulo;
	private String leilaoDescricao;
	private String leilaoAbertura;
	private String leilaoFechamento;
	
}
