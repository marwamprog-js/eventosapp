package com.eventosapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Convidado {

	@Id	
	@NotEmpty(message = "Favor preencher o rg do convidado.")
	@NotNull(message = "Favor preencher o rg do convidado.")
	private String rg;
	
	@NotEmpty(message = "Favor preencher o nome do convidado.")
	@NotNull(message = "Favor preencher o nome do convidado.")
	private String nomeConvidado;
	
	@ManyToOne
	private Evento evento;
	
	
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeConvidado() {
		return nomeConvidado;
	}
	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
}
