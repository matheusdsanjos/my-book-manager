package br.edu.ifsp.demo.my_book_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class LivroRequestDTO {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 2, max = 100, message = "Título deve ter entre 2 e 100 caracteres")
    private String titulo;

    @Size(max = 20, message = "ISBN deve ter no máximo 20 caracteres")
    private String isbn;

    private Integer anoPublicacao;

    @Positive(message = "Preço deve ser positivo")
    private BigDecimal preco;

    @NotNull(message = "ID do autor é obrigatório")
    private Long autorId;

    @NotNull(message = "ID da editora é obrigatório")
    private Long editoraId;

    // Construtor padrão
    public LivroRequestDTO() {}

    // Construtor com parâmetros
    public LivroRequestDTO(String titulo, String isbn, Integer anoPublicacao, 
                          BigDecimal preco, Long autorId, Long editoraId) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
        this.autorId = autorId;
        this.editoraId = editoraId;
    }

    // Getters e Setters
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

    public Long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(Long editoraId) {
        this.editoraId = editoraId;
    }

    @Override
    public String toString() {
        return "LivroRequestDTO{" +
                "titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", preco=" + preco +
                ", autorId=" + autorId +
                ", editoraId=" + editoraId +
                '}';
    }
} 