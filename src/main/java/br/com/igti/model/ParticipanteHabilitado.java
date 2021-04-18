package br.com.igti.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ParticipanteHabilitado {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Leilao leilao;	
	private Long idParticipante;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHabilitacao;
}
