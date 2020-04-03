package br.com.brewer.repository.helper.cerveja;

import java.util.List;

import br.com.brewer.model.Cerveja;
import br.com.brewer.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public List<Cerveja> filtrar(CervejaFilter filtro);
	
}
