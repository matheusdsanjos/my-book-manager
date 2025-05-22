package com.mybookmanager.servlet;

import com.mybookmanager.dao.EditoraDAO;
import com.mybookmanager.model.Editora;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editoras")
public class EditoraServlet extends HttpServlet {

    private EditoraDAO dao = new EditoraDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        List<Editora> editoras = dao.listar();
        req.setAttribute("editoras", editoras);

        // Preenche dados do formulário se houver parâmetros para edição
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String cidade = req.getParameter("cidade");

        if (id != null && !id.isEmpty()) {
            req.setAttribute("id", id);
            req.setAttribute("nome", nome);
            req.setAttribute("cidade", cidade);
        }

        req.getRequestDispatcher("editora-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String cidade = req.getParameter("cidade");

        if (id == null || id.isEmpty()) {
            dao.inserir(new Editora(0, nome, cidade));
        } else {
            dao.atualizar(new Editora(Integer.parseInt(id), nome, cidade));
        }

        String deleteId = req.getParameter("deleteId");
        if (deleteId != null && !deleteId.isEmpty()) {
            dao.remover(Integer.parseInt(deleteId));
        }

        resp.sendRedirect("editoras");
    }
}
