<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Autores</title>
</head>
<body>

    <h2>Lista de Autores</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Nacionalidade</th>
            <th>Ações</th>
        </tr>

        <c:forEach var="autor" items="${autores}">
            <tr>
                <td>${autor.id}</td>
                <td>${autor.nome}</td>
                <td>${autor.nacionalidade}</td>
                <td>
                    <form action="autores" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="${autor.id}" />
                        <input type="hidden" name="nome" value="${autor.nome}" />
                        <input type="hidden" name="nacionalidade" value="${autor.nacionalidade}" />
                        <button type="submit">Editar</button>
                    </form>

                    <form action="autores" method="post" style="display:inline;">
                        <input type="hidden" name="deleteId" value="${autor.id}" />
                        <button type="submit" onclick="return confirm('Confirmar exclusão?')">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/><br/>

    <h3>Adicionar/Editar Autor</h3>

    <form action="autores" method="post">
        <input type="hidden" name="id" value="${id}" />

        <p>
            Nome:<br/>
            <input type="text" name="nome" value="${nome}" required />
        </p>

        <p>
            Nacionalidade:<br/>
            <input type="text" name="nacionalidade" value="${nacionalidade}" />
        </p>

        <button type="submit">Salvar</button>
    </form>

    <br/>

    <a href="index.jsp">Voltar</a>

</body>
</html>
