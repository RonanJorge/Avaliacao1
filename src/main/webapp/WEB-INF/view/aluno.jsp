<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Cadastro Aluno</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div class="conteiner" align="center">
		<h1>Cadastro de Alunos</h1>
		<br />
		<form action="aluno" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input type="number" min="1" step="1"
						id="cpf" name="cpf" placeholder="CPF"
						value='<c:out value="${aluno.cpf }"/>'
						class="input-group input-group-lg" >
					</td>
					<td colspan="1">
						<input type="submit"
						id="botao" name="botao" value="Buscar"
						class="btn btn-primary">
					</td>				
				</tr>		
				<tr>
					<td colspan="4">
						<input type="text" 
						id="nome" name="nome" placeholder="Nome"
						value='<c:out value="${aluno.nome }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" 
						id="nomeSocial" name="nomeSocial" placeholder="Nome Social"
						value='<c:out value="${aluno.nomeSocial }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="date" 
						id="nascimento" name="nascimento"
						value='<c:out value="${aluno.nascimento }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" 
						id="email" name="email" placeholder="E-mail"
						value='<c:out value="${aluno.email }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" 
						id="emailCorporativo" name="emailCorporativo" placeholder="E-mail Corporativo"
						value='<c:out value="${aluno.emailCorporativo }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="date" 
						id="conclusaoEM" name="conclusaoEM"
						value='<c:out value="${aluno.conclusaoEM }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="number" 
						id="anoIngresso" name="anoIngresso" placeholder="Ano de Ingresso"
						value='<c:out value="${aluno.anoIngresso }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="number" 
						id="semestreIngresso" name="semestreIngresso" placeholder="Semestre de Ingresso"
						value='<c:out value="${aluno.semestreIngresso }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="Inserir"
						class="btn btn-success">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Atualizar"
						class="btn btn-warning">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Excluir"
						class="btn btn-danger">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Listar"
						class="btn btn-info">
					</td>								
				</tr>
			</table>
		</form>
	</div>
	<br />
	<div class="conteiner" align="center">
		<c:if test="${not empty saida }">
			<h2 style="color: blue;"><c:out value="${saida }" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty erro }">
			<h2 style="color: red;"><c:out value="${erro }" /></h2>
		</c:if>
	</div>
	<div class="conteiner" align="center">
		<c:if test="${not empty alunos }">
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>Nome Social</th>
						<th>Dt Nasc</th>
						<th>E-mail</th>
						<th>E-mail Corporativo</th>
						<th>Conclusao do Segundo Grau</th>
						<th>Ano de Ingresso</th>
						<th>Semestre de Ingresso</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="a" items="${alunos }">
						<tr>
							<td>${a.cpf }</td>
							<td>${a.nome }</td>
							<td>${a.nomeSocial }</td>
							<td>${a.dtNasc }</td>
							<td>${a.email }</td>
							<td>${a.emailCorporativo }</td>
							<td>${a.conclEM }</td>
							<td>${a.anoIngresso }</td>
							<td>${a.semestreIngresso }</td>
							<td><a href="aluno?acao=editar&cpf=${a.cpf }">EDITAR</a></td>
							<td><a href="aluno?acao=excluir&cpf=${a.cpf }">EXCLUIR</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>