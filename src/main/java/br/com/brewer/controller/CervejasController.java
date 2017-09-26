package br.com.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.brewer.model.Cerveja;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result) {
		if (result.hasErrors())
			System.out.println(">>>> Contem erros sim!");
		
		System.out.println(">>>> sku: " + cerveja.getSku());
		System.out.println(">>>> nome: " + cerveja.getNome());
		
		return "cerveja/CadastroCerveja";
	}

}
