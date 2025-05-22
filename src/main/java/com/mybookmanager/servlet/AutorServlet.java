package com.mybookmanager.servlet;

import com.mybookmanager.dao.AutorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.mybookmanager.model.Autor;

import java.io.IOException;
import java.util.List;

@WebServlet("/autores")
public class AutorServlet extends HttpServlet {

    private AutorDAO dao = new AutorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        List<Autor> autores = dao.listar();
        req.setAttribute("autores", autores);

        // Captura parâmetros para edição e seta no request para preencher formulário
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String nacionalidade = req.getParameter("nacionalidade");

        if (id != null && !id.isEmpty()) {
            req.setAttribute("id", id);
            req.setAttribute("nome", nome);
            req.setAttribute("nacionalidade", nacionalidade);
        }

        req.getRequestDispatcher("autor-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String nacionalidade = req.getParameter("nacionalidade");

        if (id == null || id.isEmpty()) {
            dao.inserir(new Autor(0, nome, nacionalidade));
        } else {
            dao.atualizar(new Autor(Integer.parseInt(id), nome, nacionalidade));
        }

        String deleteId = req.getParameter("deleteId");
        if (deleteId != null && !deleteId.isEmpty()) {
            dao.remover(Integer.parseInt(deleteId));
        }

        resp.sendRedirect("autores");
    }
}
