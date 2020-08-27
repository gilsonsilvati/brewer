package br.com.brewer.repository.helper.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.brewer.model.Usuario;

public class UsuariosImpl implements UsuariosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		return manager.createQuery("FROM Usuario WHERE lower(email) = lower(:email) AND ativo = true", Usuario.class)
				.setParameter("email", email)
				.getResultList().stream().findFirst();
	}

}
