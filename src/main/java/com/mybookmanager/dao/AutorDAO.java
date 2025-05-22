package com.mybookmanager.dao;

import com.mybookmanager.model.Autor;
import com.mybookmanager.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void inserir(Autor autor) {
        String sql = "INSERT INTO autor (nome, nacionalidade) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listar() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autor";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor a = new Autor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("nacionalidade")
                );
                autores.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }

    public void atualizar(Autor autor) {
        String sql = "UPDATE autor SET nome=?, nacionalidade=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM autor WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
