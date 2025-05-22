package com.mybookmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:mysql://localhost:3306/livraria?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "123456";

    static {
        try {
            // Registra o driver explicitamente
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL registrado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Falha ao registrar o driver MySQL:");
            e.printStackTrace();
            throw new RuntimeException("Driver MySQL não encontrado", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Tentando conectar ao banco...");
        System.out.println("URL: " + URL);
        System.out.println("Usuário: " + USER);
        
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Conexão estabelecida com sucesso!");
        return conn;
    }
}