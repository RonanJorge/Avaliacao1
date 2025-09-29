<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<title>Consulta de Matrícula</title>
</head>
<body>
    <div align="center">
        <jsp:include page="menu.jsp" />
    </div>

    <br />

    <div class="container" align="center">
        <h1>Consultar Matrícula</h1>
        <br />
        <form action="matricula" method="post">
            <table>
            	<tr>
					<td colspan="4">
						<input type="hidden" 
						id="codigoMatricula" name="codigoMatricula" placeholder="Codigo Matricula"
						value='<c:out value="0" />'
						class="input-group input-group-lg" readonly>
					</td>
				</tr>
                <tr>
                    <td align="right">
                        <label for="ra">RA do Aluno:</label>
                    </td>
                    <td>
                        <input type="text" id="ra" name="ra" placeholder="Digite o RA"
                               class="form-control form-control-lg"
                               value='<c:out value="${param.ra}"/>'>
                    </td>
                    <td>
                        <input type="submit" value="Buscar RA" class="btn btn-dark">
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <br />

    <div class="container" align="center">
        <c:if test="${not empty erro}">
            <h2 style="color: red;"><c:out value="${erro}" /></h2>
        </c:if>
    </div>

    <c:if test="${not empty aluno}">
        <div class="container" align="center">
            <h3>Aluno: <c:out value="${aluno.nome}" /></h3>
            <h4>Curso: <c:out value="${aluno.codigoCurso}" /></h4>
        </div>

        <br />

        <div class="container" align="center">
            <table class="table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Código Disciplina</th>
                        <th>Nome da Disciplina</th>
                        <th>Início </th>
                        <th>Horas Semanais </th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${disciplinas}">
                        <tr>
                            <td><c:out value="${d.codigoDisc}" /></td>
                            <td><c:out value="${d.nome}" /></td>
                            <td><c:out value="${d.inicio}" /></td>
                            <td><c:out value="${d.horasSemanais}" /></td>
                            <td>
                                <!-- Link que faz GET para matricular -->
                                <a href="matricula?acao=matricular&ra=${aluno.ra}&codigoDisciplina=${d.codigoDisc}">
                                    MATRICULAR
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <!-- Mensagem de saída da matrícula -->
    <c:if test="${not empty saida}">
        <div class="container" align="center">
            <h4><c:out value="${saida}" escapeXml="false"/></h4>
        </div>
    </c:if>
</body>
</html>
