package br.com.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brewer.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long> {

}
