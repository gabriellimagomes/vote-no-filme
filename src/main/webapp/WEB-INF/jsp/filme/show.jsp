<head>
	<title>Filme [show]</title>
</head>
<body>
	<p>
		<b>Nome:</b>
		${filme.nome}
	</p>

	<a href="${pageContext.request.contextPath}/filmes/${filme.id}/edit">Edit</a>
	<a href="${pageContext.request.contextPath}/filmes">Back</a>
</body>