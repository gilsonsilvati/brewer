var Brewer = Brewer || {}; // Se não existir cria um novo

Brewer.MaskMoney = (function() {
	
	function MaskMoney() { // Função construtora
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.plain.maskMoney({ precision: 0, thousands: '.' });
	}
	
	return MaskMoney;
}());

/* Função após a página ser carregada */
$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
});
