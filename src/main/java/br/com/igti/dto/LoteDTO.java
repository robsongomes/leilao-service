package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoteDTO {

	private String descricao;
	private String precoInicial;
	private String leilaoId;
}
