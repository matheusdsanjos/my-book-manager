package com.mybookmanager.dao;

import com.mybookmanager.model.Editora;
import com.mybookmanager.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {

    public void inserir(Editora editora) {
        String sql = "INSERT INTO editora (nome, cidade) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getCidade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Editora> listar() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Editora e = new Editora(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cidade")
                );
                editoras.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return editoras;
    }

    public void atualizar(Editora editora) {
        String sql = "UPDATE editora SET nome=?, cidade=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, editora.getNome());
            stmt.setString(2, editora.getCidade());
            stmt.setInt(3, editora.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM editora WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}