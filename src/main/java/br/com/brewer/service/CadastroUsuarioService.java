package br.com.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brewer.model.Usuario;
import br.com.brewer.repository.Usuarios;
import br.com.brewer.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmailIgnoreCase(usuario.getEmail());
		
		if (usuarioExistente.isPresent()) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		usuarios.save(usuario);
	}

}