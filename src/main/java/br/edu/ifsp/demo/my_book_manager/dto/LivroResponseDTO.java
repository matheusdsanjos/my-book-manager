package br.edu.ifsp.demo.my_book_manager.dto;

import java.math.BigDecimal;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String isbn;
    private Integer anoPublicacao;
    private BigDecimal preco;
    private Long autorId;
    private String autorNome;
    private Long editoraId;
    private String editoraNome;

    // Construtor padrão
    public LivroResponseDTO() {}

    // Construtor com parâmetros
    public LivroResponseDTO(Long id, String titulo, String isbn, Integer anoPublicacao, 
                           BigDecimal preco, Long autorId, String autorNome, 
                           Long editoraId, String editoraNome) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
        this.autorId = autorId;
        this.autorNome = autorNome;
        this.editoraId = editoraId;
        this.editoraNome = editoraNome;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getAutorNome() {
        return autorNome;
    }

    public void setAutorNome(String autorNome) {
        this.autorNome = autorNome;
    }

    public Long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Long editoraId) {
        this.editoraId = editoraId;
    }

    public String getEditoraNome() {
        return editoraNome;
    }

    public void setEditoraNome(String editoraNome) {
        this.editoraNome = editoraNome;
    }

    @Override
    public String toString() {
        return "LivroResponseDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", preco=" + preco +
                ", autorId=" + autorId +
                ", autorNome='" + autorNome + '\'' +
                ", editoraId=" + editoraId +
                ", editoraNome='" + editoraNome + '\'' +
                '}';
    }
} 