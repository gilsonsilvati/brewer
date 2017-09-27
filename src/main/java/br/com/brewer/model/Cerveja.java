package br.com.brewer.model;

import javax.validation.constraints.NotBlank;

public class Cerveja {
	
	@NotBlank
	private String sku;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}

}
