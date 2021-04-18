package br.com.igti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParticipanteHabilitadoRespostaDTO {

	private String id;
	private String leilaoId;
	private String leilaoTitulo;
	private String idParticipante;	
	private String dataHabilitacao;
}
