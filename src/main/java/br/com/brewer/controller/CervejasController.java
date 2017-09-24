package br.com.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cervejas")
public class CervejasController {
	
	@RequestMapping("/novo")
	public String novo() {
		return "cerveja/CadastroCerveja";
	}

}
