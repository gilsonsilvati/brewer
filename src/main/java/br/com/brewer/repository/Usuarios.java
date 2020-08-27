package br.com.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brewer.model.Usuario;
import br.com.brewer.repository.helper.usuario.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	public Optional<Usuario> findByEmailIgnoreCase(String email);

}
