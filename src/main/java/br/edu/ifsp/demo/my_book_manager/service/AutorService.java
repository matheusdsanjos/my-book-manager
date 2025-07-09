package br.edu.ifsp.demo.my_book_manager.service;

import br.edu.ifsp.demo.my_book_manager.exception.ResourceNotFoundException;
import br.edu.ifsp.demo.my_book_manager.model.Autor;
import br.edu.ifsp.demo.my_book_manager.repository.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor buscarPorIdOuLancarExcecao(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado com ID: " + id));
    }

    public List<Autor> buscarPorNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Autor> buscarPorNacionalidade(String nacionalidade) {
        return autorRepository.findByNacionalidade(nacionalidade);
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public void excluir(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Autor não encontrado com ID: " + id);
        }
        autorRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return autorRepository.existsById(id);
    }
} 