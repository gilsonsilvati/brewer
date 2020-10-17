Brewer = Brewer || {};

Brewer.PesquisaRapidaCliente = (function() {

	function PesquisaRapidaCliente() {
		this.pesquisaRapidaClientesModal = $('#pesquisaRapidaClientes');
		this.nomeInput = $('#nomeClienteModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-cliente-btn');
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaClientes');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-cliente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaCliente.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaClientesModal.on('shown.bs.modal', onModalShow.bind(this));
		this.pesquisaRapidaClientesModal.on('hide.bs.modal', onModalClose.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onModalClose() {
		this.nomeInput.val('');
		$('.js-tabela-cliente-pesquisa-rapida tr').remove();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
	
		$.ajax({
			url: this.pesquisaRapidaClientesModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			},
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaPesquisaRapidaCliente = new Brewer.TabelaPesquisaRapidaCliente(this.pesquisaRapidaClientesModal);
		tabelaPesquisaRapidaCliente.iniciar();
	}
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaCliente;
}());

Brewer.TabelaPesquisaRapidaCliente = (function() {

	function TabelaPesquisaRapidaCliente(modal) {
		this.modalCliente = modal;
		this.cliente = $('.js-cliente-pesquisa-rapida');
	}
	
	TabelaPesquisaRapidaCliente.prototype.iniciar = function() {
		this.cliente.on('click', onClienteSelecionado.bind(this));
	}
	
	function onClienteSelecionado(evento) {
		this.modalCliente.modal('hide');
		
		var clienteSelecionado = $(evento.currentTarget);
		$('#nomeCliente').val(clienteSelecionado.data('nome'));
		$('#codigoCliente').val(clienteSelecionado.data('codigo'));
	}
	
	return TabelaPesquisaRapidaCliente;
}());

$(function() {
	var pesquisaRapidaCliente = new Brewer.PesquisaRapidaCliente();
	pesquisaRapidaCliente.iniciar();
});
