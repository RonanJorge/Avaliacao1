<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
<title>Disciplinas Matriculadas</title>
</head>
<body>
    <div align="center">
        <jsp:include page="menu.jsp" />
    </div>

    <br />

    <div class="container" align="center">
        <h1>Consultar Disciplinas Matriculadas</h1>
        <br />
        <form action="disciplinasMatriculadas" method="get">
            <table>
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
                        <input type="submit" value="Buscar" class="btn btn-dark">
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <br />

    <c:if test="${not empty erro}">
        <div class="container" align="center">
            <h2 style="color: red;"><c:out value="${erro}" /></h2>
        </div>
    </c:if>

    <c:if test="${not empty disciplinas}">
        <div class="container" align="center">
            <h3>Disciplinas Matriculadas para RA: <c:out value="${param.ra}" /></h3>
            <br />
            <table class="table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Código Disciplina</th>
                        <th>Nome da Disciplina</th>
                        <th>Início</th>
                        <th>Horas Semanais</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${disciplinas}">
                        <tr>
                            <td><c:out value="${d.codigoDisc}" /></td>
                            <td><c:out value="${d.nome}" /></td>
                            <td><c:out value="${d.inicio}" /></td>
                            <td><c:out value="${d.horasSemanais}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</body>
</html>
