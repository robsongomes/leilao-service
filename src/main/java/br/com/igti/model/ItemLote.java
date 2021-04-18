package br.com.igti.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ItemLote {

	@Id
	@GeneratedValue
	private Long id;	
	@ManyToOne
	private Lote lote;
	private String descricao;
	private BigDecimal quantidade;
	@ElementCollection
	private List<String> imagens;
}


