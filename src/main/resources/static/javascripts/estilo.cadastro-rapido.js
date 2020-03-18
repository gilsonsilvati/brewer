/* Função após a página ser carregada */
$(function() {
	
	var modal = $('#modalCadastroRapidoEstilo');
	var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
	var form = modal.find('form');
	
	/* Parar de submeter formulário ao clicar no 'Enter' */
	form.on('submit', function(event) { event.preventDefault() });
	
	var url = form.attr('action');
	var inputNomeEstilo = $('#nomeEstilo');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	
	/* (shown.bs.modal) -> Evento após modal abrir */
	modal.on('shown.bs.modal', onModalShow);
	
	/* (hide.bs.modal) -> Evento após modal fechar */
	modal.on('hide.bs.modal', onModalClose);
	
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	/* Evento focus no input */
	function onModalShow() {
		inputNomeEstilo.focus();
	}
	
	/* Limpar input */
	function onModalClose() {
		inputNomeEstilo.val('');
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	/* Fazendo uma chamada Ajax */
	function onBotaoSalvarClick() {
		var nomeEstilo = inputNomeEstilo.val().trim();
		
		$.ajax({
			url: url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeEstilo }),
			error: onErroSalvandoEstilo,
			success: onEstiloSalvo
		});
	}
	
	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText; // Pegar div de mensagem
		
		containerMensagemErro.removeClass('hidden'); // Removendo classe para mostrar mensagem
		containerMensagemErro.html('<span>' + mensagemErro + '</span>'); // Add mensagem
		form.find('.form-group').addClass('has-error'); // Campos vermelhos
	}
	
	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo'); // Pegando select
		
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>'); // Preenchendo com valor vindo do controller
		comboEstilo.val(estilo.codigo); // Já ficar selecionado
		modal.modal('hide'); // Fechar modal
	}
	
});