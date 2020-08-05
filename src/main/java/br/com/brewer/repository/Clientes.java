package br.com.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brewer.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long> {
	
	public Optional<Cliente> findByNomeIgnoreCase(String nome);

	public Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

}
