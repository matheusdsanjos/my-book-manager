<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editoras</title>
</head>
<body>

    <h2>Lista de Editoras</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cidade</th>
            <th>Ações</th>
        </tr>

        <c:forEach var="editora" items="${editoras}">
            <tr>
                <td>${editora.id}</td>
                <td>${editora.nome}</td>
                <td>${editora.cidade}</td>
                <td>
                    <!-- Botão Editar: envia dados via GET para preencher o formulário -->
                    <form action="editoras" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="${editora.id}" />
                        <input type="hidden" name="nome" value="${editora.nome}" />
                        <input type="hidden" name="cidade" value="${editora.cidade}" />
                        <button type="submit">Editar</button>
                    </form>

                    <!-- Botão Excluir -->
                    <form action="editoras" method="post" style="display:inline;">
                        <input type="hidden" name="deleteId" value="${editora.id}" />
                        <button type="submit" onclick="return confirm('Confirmar exclusão?')">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/><br/>

    <h3>Adicionar/Editar Editora</h3>

    <form action="editoras" method="post">
        <input type="hidden" name="id" value="${id}" />

        <p>
            Nome:<br/>
            <input type="text" name="nome" value="${nome}" required />
        </p>

        <p>
            Cidade:<br/>
            <input type="text" name="cidade" value="${cidade}" />
        </p>

        <button type="submit">Salvar</button>
    </form>

    <br/>

    <a href="index.jsp">Voltar</a>

</body>
</html>
