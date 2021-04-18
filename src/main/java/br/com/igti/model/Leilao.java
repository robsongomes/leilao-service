package br.com.igti.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Leilao {

	@Id
	@GeneratedValue
	private Long id;
	private String titulo;
	private Long idLeiloeiro;
	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date abertura;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechamento;
	// TODO: Seria melhor usar um Enum?
	private String modalidade;
	
	public boolean isLeilaoAbertoParaReceberLances() {
		Date currentDate = new Date();
		return (new Date().equals(abertura) || currentDate.equals(fechamento))
	              || (currentDate.before(fechamento) && currentDate.after(abertura));
	}
	
	public boolean isLeilaoAbertoParaHabilitacao() {		
		return new Date().before(abertura);
	}
}
