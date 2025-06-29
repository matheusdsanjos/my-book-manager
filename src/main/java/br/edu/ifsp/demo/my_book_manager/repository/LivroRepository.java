package br.edu.ifsp.demo.my_book_manager.repository;

import br.edu.ifsp.demo.my_book_manager.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    // Busca livros por título (ignorando maiúsculas/minúsculas)
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    
    // Busca livros por ISBN
    Livro findByIsbn(String isbn);
    
    // Busca livros por autor
    List<Livro> findByAutorId(Long autorId);
    
    // Busca livros por editora
    List<Livro> findByEditoraId(Long editoraId);
    
    // Busca livros por ano de publicação
    List<Livro> findByAnoPublicacao(Integer anoPublicacao);
    
    // Busca livros por faixa de preço
    List<Livro> findByPrecoBetween(java.math.BigDecimal precoMin, java.math.BigDecimal precoMax);
    
    // Busca personalizada por título, autor ou ISBN
    @Query("SELECT l FROM Livro l WHERE " +
           "LOWER(l.titulo) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.autor.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.isbn) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Livro> buscarPorTituloAutorOuIsbn(@Param("termo") String termo);
    
    // Busca livros com informações completas (autor e editora)
    @Query("SELECT l FROM Livro l JOIN FETCH l.autor JOIN FETCH l.editora")
    List<Livro> findAllWithAutorAndEditora();
} 