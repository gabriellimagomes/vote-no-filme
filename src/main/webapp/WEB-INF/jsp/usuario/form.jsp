<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/usuarios" method="post" role="form">
  
	<c:if test="${not empty usuario.id}">
		<input type="hidden" name="usuario.id" value="${usuario.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>
	<div class="form-group">
		<label for="nome">Nome</label>
		<input type="text" name="usuario.nome" id="nome" class="form-control" value="${usuario.nome}" placeholder="Nome do usuário"/>
	</div>
	<div class="form-group">
		<label for="email">Email</label>
		<input type="email" name="usuario.email" id="email" class="form-control" value="${usuario.email}" placeholder="Email do usuário"/>
	</div>
	
	<button type="submit" class="btn btn-primary">Salvar</button>
</form>
