package com.mybookmanager.model;

public class Livro {
    private int id;
    private String titulo;
    private String isbn;
    private int anoPublicacao;
    private double preco;
    private int autorId;
    private int editoraId;
    private String autorNome;
    private String editoraNome;

    // Construtores, getters e setters
    public Livro() {}

    public Livro(int id, String titulo, String isbn, int anoPublicacao, 
                double preco, int autorId, int editoraId) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
        this.autorId = autorId;
        this.editoraId = editoraId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getAutorId() { return autorId; }
    public void setAutorId(int autorId) { this.autorId = autorId; }
    public int getEditoraId() { return editoraId; }
    public void setEditoraId(int editoraId) { this.editoraId = editoraId; }
    public String getAutorNome() { return autorNome; }
    public void setAutorNome(String autorNome) { this.autorNome = autorNome; }
    public String getEditoraNome() { return editoraNome; }
    public void setEditoraNome(String editoraNome) { this.editoraNome = editoraNome; }
}
