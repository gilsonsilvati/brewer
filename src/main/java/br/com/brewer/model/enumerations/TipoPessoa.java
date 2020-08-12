package br.com.brewer.model.enumerations;

import br.com.brewer.model.validation.group.CnpjGroup;
import br.com.brewer.model.validation.group.CpfGroup;

public enum TipoPessoa {
	
	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class) {
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};
	
	private final String descricao;
	private final String documento;
	private final String mascara;
	private final Class<?> group;
	
	private TipoPessoa(String descricao, String documento, String mascara, Class<?> group) {
		this.descricao = descricao;
		this.documento = documento;
		this.mascara = mascara;
		this.group = group;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGroup() {
		return group;
	}
	
	public static String removerFormatacao(String cpfOuCnpj) {
		return cpfOuCnpj.replaceAll("\\.|-|/", "");
	}
	
	public abstract String formatar(String cpfOuCnpj);
	
}
