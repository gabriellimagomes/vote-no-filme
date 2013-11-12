<head>
	<title>Voto [index]</title>
</head>
<body>
	<h1>Listing Votos</h1>

	<table>
		<tr>
			<th></th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${votoList}" var="voto">
			<tr>
				<td><a href="${pageContext.request.contextPath}/votos/${voto.id}">show</a></td>
				<td><a href="${pageContext.request.contextPath}/votos/${voto.id}/edit">edit</a></td>
				<td>
					<form action="${pageContext.request.contextPath}/votos/${voto.id}" method="post">
						<input type="hidden" name="_method" value="delete"/>
						<button type="submit" onclick="return confirm('Are you sure?')">destroy</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br />
	<a href="${pageContext.request.contextPath}/votos/new">New Voto</a> 
</body>