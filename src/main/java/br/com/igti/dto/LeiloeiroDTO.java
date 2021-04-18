package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeiloeiroDTO {
	private Long id;
	private String cnpj;
	private String razaoSocial;
}