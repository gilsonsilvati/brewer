package br.com.brewer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.brewer.controller.page.PageWrapper;
import br.com.brewer.model.Cliente;
import br.com.brewer.model.enumerations.TipoPessoa;
import br.com.brewer.repository.Clientes;
import br.com.brewer.repository.Estados;
import br.com.brewer.repository.filter.ClienteFilter;
import br.com.brewer.service.CadastroClienteService;
import br.com.brewer.service.exception.CpfCnpjJaCadastradoException;

@Controller
@RequestMapping("clientes")
public class ClientesController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cliente/PesquisaClientes");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Cliente> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return clientes.findByNomeStartingWithIgnoreCase(nome);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/CadastroCliente");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		
		try {
			cadastroClienteService.salvar(cliente);
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
		} catch (CpfCnpjJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		return new ModelAndView("redirect:/clientes/novo");
	}
	
	// Pode ser feito com Bean Validation, aqui é só para questões de exemplo.
	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	// IllegalArgumentException somente nesta classe.
	@ExceptionHandler(IllegalArgumentException.class)
	private ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
 
}
