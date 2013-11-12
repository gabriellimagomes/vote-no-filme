<head>
	<title>Filme [index]</title>
</head>
<body>
	<h1>Listing Filmes</h1>

	<table>
		<tr>
			<th>Nome</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${filmeList}" var="filme">
			<tr>
				<td>${filme.nome}</td>
				<td><a href="${pageContext.request.contextPath}/filmes/${filme.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/filmes/${filme.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/filmes/${filme.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/filmes/new">New Filme</a> 
</body>