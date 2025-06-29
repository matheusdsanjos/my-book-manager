package br.edu.ifsp.demo.my_book_manager.service;

import br.edu.ifsp.demo.my_book_manager.exception.ResourceNotFoundException;
import br.edu.ifsp.demo.my_book_manager.model.Editora;
import br.edu.ifsp.demo.my_book_manager.repository.EditoraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public List<Editora> listarTodos() {
        return editoraRepository.findAll();
    }

    public Optional<Editora> buscarPorId(Long id) {
        return editoraRepository.findById(id);
    }

    public Editora buscarPorIdOuLancarExcecao(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Editora não encontrada com ID: " + id));
    }

    public List<Editora> buscarPorNome(String nome) {
        return editoraRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Editora> buscarPorCidade(String cidade) {
        return editoraRepository.findByCidade(cidade);
    }

    public Editora salvar(Editora editora) {
        return editoraRepository.save(editora);
    }

    public void excluir(Long id) {
        if (!editoraRepository.existsById(id)) {
            throw new ResourceNotFoundException("Editora não encontrada com ID: " + id);
        }
        editoraRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return editoraRepository.existsById(id);
    }
} 