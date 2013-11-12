<c:if test="${not empty errors}">
	<c:forEach items="${errors}" var="error">
		${error.category} - ${error.message}<br />
	</c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/votos" method="post">
  
	<c:if test="${not empty voto.id}">
		<input type="hidden" name="voto.id" value="${voto.id}"/>
		<input type="hidden" name="_method" value="put"/>
	</c:if>

  <div class="actions">
	  <button type="submit">send</button>
	</div>
</form>

<a href="${pageContext.request.contextPath}/votos">Back</a>
