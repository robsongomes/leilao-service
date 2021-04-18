package br.com.igti.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemLoteDTO {
	
	private String loteId;
	private String descricao;
	private String quantidade;
	private List<String> imagens;
}
