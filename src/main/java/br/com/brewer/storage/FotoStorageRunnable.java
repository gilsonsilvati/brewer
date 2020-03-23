package br.com.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<String> resultado;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<String> resultado) {
		this.files = files;
		this.resultado = resultado;
	}

	/* Aqui processa a operação demorada */
	@Override
	public void run() {
		System.out.println(">>> files: " + files[0].getSize());
		
		// TODO: Salvar a foto no Sistema de Arquivos...
		
		resultado.setResult("OK! Foto recebida!");
	}

}
