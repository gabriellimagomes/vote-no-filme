(function($){
	
	var $btnfilme1 = $('#btnFilme1'),
		$btnfilme2 = $('#btnFilme2'),
		$hiddenFilmeVotado = $('#hiddenFilmeVotado');
	
	$('#btnFilme1, #btnFilme2').click(function(){
		var idFilmeVotado = $(this).data('idFilme');
		
		usuario.votar($btnfilme1.data('idFilme'), $btnfilme2.data('idFilme'), idFilmeVotado, $hiddenFilmeVotado);
	});
	
})(jQuery);