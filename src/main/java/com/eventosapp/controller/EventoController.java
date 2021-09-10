package com.eventosapp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ConvidadeRepository;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadeRepository convidadeRepository;
	
	
	/*
	 * Chama a página de Cadastra Evento
	 * */
	@GetMapping("/cadastrar-evento")
	public String evento() {
		return "evento/form-evento";
	}
	
	
	/*
	 * SALVA EVENTOS
	 * */
	@PostMapping("/cadastrar-evento")
	public String salvar(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrar-evento"; 
		}
		
		eventoRepository.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento Cadastrado com sucesso!");
		
		return "redirect:/cadastrar-evento";
	}
	
	
	/*
	 * 
	 * Lista EVENTOS
	 * */
	@GetMapping("/eventos")
	public ModelAndView listaEventos() {
		
		ModelAndView mv = new ModelAndView("index");
		
		Iterable<Evento> eventos = eventoRepository.findAll();
		
		mv.addObject("eventos", eventos);
		
		return mv;
		
	}
	
	
	/**
	 * DELETAR
	 * @param codigo
	 * @return
	 */
	@GetMapping("/deletar/{codigo}")
	public String deletarEvento(@PathVariable("codigo") long codigo) {
		Optional<Evento> evento = eventoRepository.findById(codigo);
		eventoRepository.delete(evento.get());
		return "redirect:/eventos";
	}
	
	
	/**
	 * DELETAR CONVIDADO
	 * @param codigo
	 * @return
	 */
	@GetMapping("/deletarConvidado/{rg}")
	public String deletarConvidado(@PathVariable("rg") String rg) {
		Convidado convidado = convidadeRepository.findByRg(rg);
		convidadeRepository.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getId();
		String codigo = "" + codigoLong;
		
		return "redirect:/" + codigo;
	}
	
	
	
	/*
	 * 
	 * Busca EVENTO por ID
	 * */
	@GetMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		
		Optional<Evento> evento = eventoRepository.findById(codigo);
		
		ModelAndView modelAndView = new ModelAndView("evento/detalhesEvento");
		modelAndView.addObject("evento", evento.get());
		
		Iterable<Convidado> convidados = convidadeRepository.findByEvento(evento.get());
		modelAndView.addObject("convidados", convidados);
		
		return modelAndView;
	}
	
	
	
	/*
	 * 
	 * SALVAR CONVIDADO por ID
	 * */
	@PostMapping("/{codigo}")
	public String salvarConvidado(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
	
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		
		Optional<Evento> evento = eventoRepository.findById(codigo);		
		convidado.setEvento(evento.get());
		
		convidadeRepository.save(convidado);
		
		attributes.addFlashAttribute("mensagem", "Convidado cadastrado com sucesso!");
		return "redirect:/{codigo}";
	}
	
}
