package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository repository;
	
	@RequestMapping(value = "evento", method = RequestMethod.GET)
	public String form() {
		return "evento/evento-form";
	}
	
	@RequestMapping(value = "evento", method = RequestMethod.POST)
	public String salvar(Evento evento) {
		
		repository.save(evento);
		
		return "redirect:/evento";
	}
	
	@RequestMapping(value = "list-eventos")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("index");
		
		Iterable<Evento> eventos = this.repository.findAll();
		
		mv.addObject("eventos", eventos);
		
		return mv;
	}
	
	@RequestMapping(value = "/{codigo}")
	public ModelAndView findById(@PathVariable Long codigo) throws Exception {
		ModelAndView mv = new ModelAndView("evento/detalhes-evento");
		
		Evento evento = repository.findByCodigo(codigo);
		
		mv.addObject("evento", evento);
		
		return mv;
	}
}
