package br.com.igti.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igti.model.Leilao;

public interface LeilaoRepository extends JpaRepository<Leilao, Long>{

	List<Leilao> findByFechamentoLessThanEqual(Date fechamento);
}
