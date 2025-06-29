package br.edu.ifsp.demo.my_book_manager.repository;

import br.edu.ifsp.demo.my_book_manager.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    
    // Busca editoras por nome (ignorando maiúsculas/minúsculas)
    List<Editora> findByNomeContainingIgnoreCase(String nome);
    
    // Busca editoras por cidade
    List<Editora> findByCidade(String cidade);
} 