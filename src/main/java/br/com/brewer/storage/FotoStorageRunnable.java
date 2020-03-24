package br.com.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import br.com.brewer.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage) {
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}

	/* Aqui processa a operação demorada */
	@Override
	public void run() {
		this.fotoStorage.salvarTemporariamente(files);
		
		String nomeFoto = files[0].getOriginalFilename();
		String contentType = files[0].getContentType();
		
		resultado.setResult(new FotoDTO(nomeFoto, contentType));
	}

}