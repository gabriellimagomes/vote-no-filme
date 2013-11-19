<form action="<c:url value="/voto" />" method="post">
	<div class="panel panel-primary">
		<div class="panel-heading"><h1 class="text-center">Votação de Filmes</h1></div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6" id="divFilme1">
					<div class="thumbnail">
						<img alt="filme para votaçao" src="<c:url value="/images/img${filme1.id}.jpg" />" class="img-thumbnail" />
						<div class="caption">
							<h3 class="text-center">${filme1.nome}</h3>
							<button class="btn btn-primary center-block" id="btnFilme1" data-id-filme="${filme1.id}">Votar</button>
						</div>
					</div>
				</div>
				<div class="col-md-6" id="divFilme2">
					<div class="thumbnail">
						<img alt="filme para votaçao" src="<c:url value="/images/img${filme2.id}.jpg" />" class="img-thumbnail" />
						<div class="caption text-center">
							<h3 class="center-block">${filme2.nome}</h3>
							<button class="btn btn-primary center-block" id="btnFilme2" data-id-filme="${filme2.id}">Votar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" name="filmeVotado.id" id="hiddenFilmeVotado" />
	<input type="hidden" name="duelo.filme1.id" value="${filme1.id}"/>
	<input type="hidden" name="duelo.filme2.id" value="${filme2.id}"/>
</form>
