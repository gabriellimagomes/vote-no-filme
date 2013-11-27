<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/filmes" method="post">
  
	<c:if test="${not empty filme.id}">
		<input type="hidden" name="filme.id" value="${filme.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

	<div class="field">
		Nome:<br />
	
		<input type="text" name="filme.nome" value="${filme.nome}"/>
	</div>
	
  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/filmes">Back</a>
