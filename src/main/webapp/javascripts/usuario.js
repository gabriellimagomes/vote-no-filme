var usuario = (function($){
	
	var votar = function(idFilme1, idFilme2, idFilmeVotado, $hiddenFilmeVotado){
		var	votoValido = idFilmeVotado === idFilme1 || idFilmeVotado === idFilme2;
		
		if (votoValido){
			$hiddenFilmeVotado.val(idFilmeVotado);
			$hiddenFilmeVotado[0].form.submit();
		}
		
		return votoValido;
	};
	
	return {
		votar: votar
	};
})(jQuery);