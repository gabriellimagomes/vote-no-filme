<div class="row">
	<div class="col-md-6">
		<table class="table table-striped table-bordered">
			<caption class="h2">Ranking do Usuário</caption>
			<thead>
				<tr>
					<th class="text-center">Filme</th>
					<th class="text-center">Nº de Votos</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rankingUsuario}" var="rankingUsu">
					<tr>
						<td>${rankingUsu.filme.nome}</td>
						<td class="text-right">${rankingUsu.quantidade}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-md-6">
		<table class="table table-striped table-bordered">
			<caption class="h2">Ranking Geral</caption>
			<thead>
				<tr>
					<th class="text-center">Filme</th>
					<th class="text-center">Nº de Votos</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rankingGeral}" var="rankingGe">
					<tr>
						<td>${rankingGe.filme.nome}</td>
						<td class="text-right">${rankingGe.quantidade}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>