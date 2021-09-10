package com.eventosapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;

@Repository
public interface ConvidadeRepository extends CrudRepository<Convidado, String> {

	Iterable<Convidado> findByEvento(Evento evento);
	
	Convidado findByRg(String rg);
	
}
