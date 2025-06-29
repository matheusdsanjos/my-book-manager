package br.edu.ifsp.demo.my_book_manager.dto;

public class AutorResponseDTO {

    private Long id;
    private String nome;
    private String nacionalidade;

    // Construtor padrão
    public AutorResponseDTO() {}

    // Construtor com parâmetros
    public AutorResponseDTO(Long id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
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

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "AutorResponseDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
} 