package br.com.igti.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Lote {
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Leilao leilao;
	private String descricao;
	private BigDecimal precoInicial;
}
