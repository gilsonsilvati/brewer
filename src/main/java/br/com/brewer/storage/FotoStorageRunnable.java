package br.com.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.brewer.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado) {
		this.files = files;
		this.resultado = resultado;
	}

	/* Aqui processa a operação demorada */
	@Override
	public void run() {
		System.out.println(">>> files: " + files[0].getSize());
		
		// TODO: Salvar a foto no Sistema de Arquivos...
		
		
		String nomeFoto = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}
