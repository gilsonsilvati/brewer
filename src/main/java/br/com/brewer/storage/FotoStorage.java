package br.com.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public String salvarTemporariamente(MultipartFile[] files);

	public byte[] recuperarFotoTemporaria(String foto);

	public void salvar(String foto);

	public byte[] recuperar(String foto);

}
