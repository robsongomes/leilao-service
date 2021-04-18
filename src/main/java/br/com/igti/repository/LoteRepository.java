package br.com.igti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igti.model.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long>{

	List<Lote> findByLeilaoId(Long idLeilao);

}
