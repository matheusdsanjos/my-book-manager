package br.edu.ifsp.demo.my_book_manager.service;

import br.edu.ifsp.demo.my_book_manager.exception.ResourceNotFoundException;
import br.edu.ifsp.demo.my_book_manager.model.Livro;
import br.edu.ifsp.demo.my_book_manager.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAllWithAutorAndEditora();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Livro buscarPorIdOuLancarExcecao(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado com ID: " + id));
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Livro buscarPorIsbn(String isbn) {
        return livroRepository.findByIsbn(isbn);
    }

    public List<Livro> buscarPorAutor(Long autorId) {
        return livroRepository.findByAutorId(autorId);
    }

    public List<Livro> buscarPorEditora(Long editoraId) {
        return livroRepository.findByEditoraId(editoraId);
    }

    public List<Livro> buscarPorAnoPublicacao(Integer ano) {
        return livroRepository.findByAnoPublicacao(ano);
    }

    public List<Livro> buscarPorFaixaPreco(BigDecimal precoMin, BigDecimal precoMax) {
        return livroRepository.findByPrecoBetween(precoMin, precoMax);
    }

    public List<Livro> buscarPorTituloAutorOuIsbn(String termo) {
        return livroRepository.buscarPorTituloAutorOuIsbn(termo);
    }

    public Livro salvar(Livro livro) {
        // Verificar se já existe um livro com o mesmo ISBN (exceto se for o mesmo livro)
        if (livro.getIsbn() != null && !livro.getIsbn().trim().isEmpty()) {
            Livro livroExistente = livroRepository.findByIsbn(livro.getIsbn());
            if (livroExistente != null && !livroExistente.getId().equals(livro.getId())) {
                throw new RuntimeException("Já existe um livro com o ISBN: " + livro.getIsbn());
            }
        }
        return livroRepository.save(livro);
    }

    public void excluir(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro não encontrado com ID: " + id);
        }
        livroRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return livroRepository.existsById(id);
    }

    public boolean existePorIsbn(String isbn) {
        return livroRepository.findByIsbn(isbn) != null;
    }
} 