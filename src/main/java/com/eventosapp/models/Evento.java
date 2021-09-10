package com.eventosapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Evento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@NotEmpty(message = "Favor preencher o nome do evento.")
	@NotNull(message = "Favor preencher o nome do evento.")
	public String nome;
	
	@NotEmpty(message = "Favor preencher o local do evento.")
	@NotNull(message = "Favor preencher o local do evento.")
	public String local;
	
	@NotEmpty(message = "Favor preencher a data do evento.")
	@NotNull(message = "Favor preencher a data do evento.")
	public String data;
	
	@NotEmpty(message = "Favor preencher o horario do evento.")
	@NotNull(message = "Favor preencher o horario do evento.")
	public String horario;
	
	@OneToMany
	private List<Convidado> convidados;
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	
	
}
