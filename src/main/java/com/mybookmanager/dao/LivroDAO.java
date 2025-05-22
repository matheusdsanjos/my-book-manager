package com.mybookmanager.dao;

import com.mybookmanager.model.Livro;
import com.mybookmanager.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) {
        String sql = "INSERT INTO livro (titulo, isbn, ano_publicacao, preco, autor_id, editora_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setDouble(4, livro.getPreco());
            stmt.setInt(5, livro.getAutorId());
            stmt.setInt(6, livro.getEditoraId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = """
            SELECT l.*, a.nome as autor_nome, e.nome as editora_nome 
            FROM livro l 
            LEFT JOIN autor a ON l.autor_id = a.id 
            LEFT JOIN editora e ON l.editora_id = e.id
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro l = new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("isbn"),
                    rs.getInt("ano_publicacao"),
                    rs.getDouble("preco"),
                    rs.getInt("autor_id"),
                    rs.getInt("editora_id")
                );
                l.setAutorNome(rs.getString("autor_nome"));
                l.setEditoraNome(rs.getString("editora_nome"));
                livros.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    // Novo método buscarPorId
    public Livro buscarPorId(int id) {
        String sql = """
            SELECT l.*, a.nome as autor_nome, e.nome as editora_nome 
            FROM livro l
            LEFT JOIN autor a ON l.autor_id = a.id
            LEFT JOIN editora e ON l.editora_id = e.id
            WHERE l.id = ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro livro = new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("isbn"),
                    rs.getInt("ano_publicacao"),
                    rs.getDouble("preco"),
                    rs.getInt("autor_id"),
                    rs.getInt("editora_id")
                );
                livro.setAutorNome(rs.getString("autor_nome"));
                livro.setEditoraNome(rs.getString("editora_nome"));
                return livro;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo=?, isbn=?, ano_publicacao=?, preco=?, autor_id=?, editora_id=? WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getIsbn());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setDouble(4, livro.getPreco());
            stmt.setInt(5, livro.getAutorId());
            stmt.setInt(6, livro.getEditoraId());
            stmt.setInt(7, livro.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        String sql = "DELETE FROM livro WHERE id=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> livros = new ArrayList<>();
        String sql = """
            SELECT l.*, a.nome as autor_nome, e.nome as editora_nome 
            FROM livro l 
            LEFT JOIN autor a ON l.autor_id = a.id 
            LEFT JOIN editora e ON l.editora_id = e.id
            WHERE l.titulo LIKE ?
            """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livro l = new Livro(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("isbn"),
                    rs.getInt("ano_publicacao"),
                    rs.getDouble("preco"),
                    rs.getInt("autor_id"),
                    rs.getInt("editora_id")
                );
                l.setAutorNome(rs.getString("autor_nome"));
                l.setEditoraNome(rs.getString("editora_nome"));
                livros.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}
