package br.com.igti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igti.model.ParticipanteHabilitado;

public interface ParticipanteHabilitadoRepository extends JpaRepository<ParticipanteHabilitado, Long> {

	public ParticipanteHabilitado findByIdParticipante(Long idParticipante);
}
