<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Livros</title>
</head>
<body>

    <h2>Lista de Livros</h2>

    <form action="livros" method="get">
        <p>
            Buscar por título: 
            <input type="text" name="busca" />
            <button type="submit">Buscar</button>
        </p>
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>ISBN</th>
            <th>Ano</th>
            <th>Preço</th>
            <th>Autor</th>
            <th>Editora</th>
            <th>Ações</th>
        </tr>

        <c:forEach var="livro" items="${livros}">
            <tr>
                <td>${livro.id}</td>
                <td>${livro.titulo}</td>
                <td>${livro.isbn}</td>
                <td>${livro.anoPublicacao}</td>
                <td>${livro.preco}</td>
                <td>${livro.autorNome}</td>
                <td>${livro.editoraNome}</td>
                <td>
                    <!-- Editar via GET com id -->
                    <form action="livros" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="${livro.id}" />
                        <button type="submit">Editar</button>
                    </form>

                    <form action="livros" method="post" style="display:inline;">
                        <input type="hidden" name="deleteId" value="${livro.id}" />
                        <button type="submit" onclick="return confirm('Confirmar exclusão?')">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br/><br/>

    <h3>Adicionar/Editar Livro</h3>

    <form action="livros" method="post">
        <!-- Preenche com livro para edição, ou vazio -->
        <input type="hidden" name="id" value="${livroEdicao != null ? livroEdicao.id : ''}" />

        <p>
            Título:<br/>
            <input type="text" name="titulo" value="${livroEdicao != null ? livroEdicao.titulo : ''}" required />
        </p>

        <p>
            ISBN:<br/>
            <input type="text" name="isbn" value="${livroEdicao != null ? livroEdicao.isbn : ''}" required />
        </p>

        <p>
            Ano Publicação:<br/>
            <input type="number" name="anoPublicacao" value="${livroEdicao != null ? livroEdicao.anoPublicacao : ''}" required />
        </p>

        <p>
            Preço:<br/>
            <input type="number" step="0.01" name="preco" value="${livroEdicao != null ? livroEdicao.preco : ''}" required />
        </p>

        <p>
            Autor:<br/>
            <select name="autorId" required>
                <option value="">Selecione</option>
                <c:forEach var="autor" items="${autores}">
                    <option value="${autor.id}" 
                        <c:if test="${livroEdicao != null && livroEdicao.autorId == autor.id}">
                            selected
                        </c:if>
                    >${autor.nome}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            Editora:<br/>
            <select name="editoraId" required>
                <option value="">Selecione</option>
                <c:forEach var="editora" items="${editoras}">
                    <option value="${editora.id}" 
                        <c:if test="${livroEdicao != null && livroEdicao.editoraId == editora.id}">
                            selected
                        </c:if>
                    >${editora.nome}</option>
                </c:forEach>
            </select>
        </p>

        <button type="submit">Salvar</button>
    </form>

    <br/>

    <a href="index.jsp">Voltar</a>

</body>
</html>
