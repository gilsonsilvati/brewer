package br.com.brewer.model.enumerations;

public enum Origem {
	
	INTERNACIONAL("Internacional"),
	NACIONAL("Nacional");
	
	private final String descricao;
	
	private Origem(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
