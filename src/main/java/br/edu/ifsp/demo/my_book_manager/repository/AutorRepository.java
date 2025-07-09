package br.edu.ifsp.demo.my_book_manager.repository;

import br.edu.ifsp.demo.my_book_manager.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    
    // Busca autores por nome (ignorando maiúsculas/minúsculas)
    List<Autor> findByNomeContainingIgnoreCase(String nome);
    
    // Busca autores por nacionalidade
    List<Autor> findByNacionalidade(String nacionalidade);
} 