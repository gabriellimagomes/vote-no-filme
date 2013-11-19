module('Voto');

test('deve retornar true quando voto for valido', function(){
	var filmeVotado = 1,
		idFilme1 = $('#btnFilme1').data('idFilme'),
		idFilme2 = $('#btnFilme2').data('idFilme'),
		$hiddenFilmeVotado = $('#hiddenFilmeVotado');
	
	ok(usuario.votar(idFilme1, idFilme2, filmeVotado, $hiddenFilmeVotado), 'filme votado estava entre os dois do duelo');
});

test('deve retornar false quando o filme votado nao estiver entre os filmes do duelo', function(){
	var filmeVotado = 3,
	idFilme1 = $('#btnFilme1').data('idFilme'),
	idFilme2 = $('#btnFilme2').data('idFilme');
	
	ok(!usuario.votar(idFilme1, idFilme2, filmeVotado), 'votacao nao foi executada porque o filme votado nao esta no duelo');
});