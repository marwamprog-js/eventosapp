package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventosapp.models.Evento;

@Repository
public interface EventoRepository extends CrudRepository<Evento, Long>{

	
	
}
