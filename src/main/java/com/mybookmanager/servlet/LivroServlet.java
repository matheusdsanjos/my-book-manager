package com.mybookmanager.servlet;

import com.mybookmanager.dao.AutorDAO;
import com.mybookmanager.dao.EditoraDAO;
import com.mybookmanager.dao.LivroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mybookmanager.model.Livro;

import java.io.IOException;
import java.util.List;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {

    private LivroDAO livroDao = new LivroDAO();
    private AutorDAO autorDao = new AutorDAO();
    private EditoraDAO editoraDao = new EditoraDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String busca = req.getParameter("busca");
        String idParam = req.getParameter("id");
        List<Livro> livros;
        
        if (busca != null && !busca.isEmpty()) {
            livros = livroDao.buscarPorTitulo(busca);
        } else {
            livros = livroDao.listar();
        }
        
        req.setAttribute("livros", livros);
        req.setAttribute("autores", autorDao.listar());
        req.setAttribute("editoras", editoraDao.listar());

        // Se tiver id, buscar livro para edição
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Livro livro = livroDao.buscarPorId(id);
                if (livro != null) {
                    req.setAttribute("livroEdicao", livro);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Pode tratar erro aqui se quiser
            }
        }

        req.getRequestDispatcher("livro-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String titulo = req.getParameter("titulo");
        String isbn = req.getParameter("isbn");

        String anoPublicacaoParam = req.getParameter("anoPublicacao");
        String precoParam = req.getParameter("preco");
        String autorIdParam = req.getParameter("autorId");
        String editoraIdParam = req.getParameter("editoraId");
        
        // Verifica e converte parâmetros numéricos, tratando valores ausentes ou inválidos
        int anoPublicacao = 0;
        double preco = 0.0;
        int autorId = 0;
        int editoraId = 0;

        try {
            if (anoPublicacaoParam != null && !anoPublicacaoParam.isEmpty()) {
                anoPublicacao = Integer.parseInt(anoPublicacaoParam);
            }
            if (precoParam != null && !precoParam.isEmpty()) {
                preco = Double.parseDouble(precoParam);
            }
            if (autorIdParam != null && !autorIdParam.isEmpty()) {
                autorId = Integer.parseInt(autorIdParam);
            }
            if (editoraIdParam != null && !editoraIdParam.isEmpty()) {
                editoraId = Integer.parseInt(editoraIdParam);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        // Verifica se está excluindo
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null && !deleteId.isEmpty()) {
            try {
                livroDao.remover(Integer.parseInt(deleteId));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("livros");
            return;
        }

        // Inserir ou atualizar
        if (id == null || id.isEmpty()) {
            livroDao.inserir(new Livro(0, titulo, isbn, anoPublicacao, preco, autorId, editoraId));
        } else {
            try {
                livroDao.atualizar(new Livro(Integer.parseInt(id), titulo, isbn, anoPublicacao, preco, autorId, editoraId));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("livros");
    }

}
