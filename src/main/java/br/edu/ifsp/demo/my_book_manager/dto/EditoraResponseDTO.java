package br.edu.ifsp.demo.my_book_manager.dto;

public class EditoraResponseDTO {

    private Long id;
    private String nome;
    private String cidade;

    // Construtor padrão
    public EditoraResponseDTO() {}

    // Construtor com parâmetros
    public EditoraResponseDTO(Long id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "EditoraResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
} 