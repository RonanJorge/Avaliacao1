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
<title>Cadastro Disciplinas</title>
</head>
<body>
	<div align="center">
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div class="conteiner" align="center">
		<h1>Cadastro de Disciplinas</h1>
		<br />
		<form action="disciplina" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input type="number" min="1" step="1"
						id="codigoDisc" name="codigoDisc" placeholder="Código da Disciplina"
						value='<c:out value="${disciplina.codigoDisc }"/>'
						class="input-group input-group-lg" >
					</td>
					<td colspan="1">
						<input type="submit"
						id="botao" name="botao" value="Buscar"
						class="btn btn-dark">
					</td>				
				</tr>
				<tr>
					<td colspan="4">
						<input type="number" min="1" step="1"
						id="codigoCurso" name="codigoCurso" placeholder="Código do Curso"
						value='<c:out value="${disciplina.codigoCurso }"/>'
						class="input-group input-group-lg" >
					</td>				
				</tr>		
				<tr>
					<td colspan="4">
						<input type="text" 
						id="nome" name="nome" placeholder="Nome da Disciplina"
						value='<c:out value="${disciplina.nome }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="time" 
						id="inicio" name="inicio" placeholder="Inicio"
						value='<c:out value="${disciplina.inicio }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="number" 
						id="horasSemanais" name="horasSemanais" placeholder="Horas Semanais"
						value='<c:out value="${disciplina.horasSemanais }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="text" 
						id="conteudos" name="conteudos" placeholder="Conteudos"
						value='<c:out value="${disciplina.conteudos }"/>'
						class="input-group input-group-lg">
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit"
						id="botao" name="botao" value="Inserir"
						class="btn btn-dark">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Atualizar"
						class="btn btn-dark">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Excluir"
						class="btn btn-dark">
					</td>								
					<td>
						<input type="submit"
						id="botao" name="botao" value="Listar"
						class="btn btn-dark">
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
		<c:if test="${not empty disciplinas }">
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th>Codigo Disciplina</th>
						<th>Codigo Curso</th>
						<th>Nome</th>
						<th>Início</th>
						<th>Horas Semanais</th>
						<th>Conteudos</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="d" items="${disciplinas }">
						<tr>
							<td>${d.codigoDisc }</td>
							<td>${d.codigoCurso }</td>
							<td>${d.nome }</td>
							<td>${d.inicio }</td>
							<td>${d.horasSemanais }</td>
							<td>${d.conteudos }</td>
							<td><a href="disciplina?acao=editar&codigoDisc=${d.codigoDisc }">EDITAR</a></td>
							<td><a href="disciplina?acao=excluir&codigoDisc=${d.codigoDisc }">EXCLUIR</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>