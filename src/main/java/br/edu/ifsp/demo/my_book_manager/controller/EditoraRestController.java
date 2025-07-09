package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.dto.EditoraRequestDTO;
import br.edu.ifsp.demo.my_book_manager.dto.EditoraResponseDTO;
import br.edu.ifsp.demo.my_book_manager.model.Editora;
import br.edu.ifsp.demo.my_book_manager.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/editoras")
public class EditoraRestController {

    private final EditoraService editoraService;

    public EditoraRestController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping
    public ResponseEntity<List<EditoraResponseDTO>> listar() {
        List<Editora> editoras = editoraService.listarTodos();
        List<EditoraResponseDTO> response = editoras.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> obterPorId(@PathVariable Long id) {
        Editora editora = editoraService.buscarPorIdOuLancarExcecao(id);
        return ResponseEntity.ok(toResponseDTO(editora));
    }

    @PostMapping
    public ResponseEntity<EditoraResponseDTO> criar(@Valid @RequestBody EditoraRequestDTO dto) {
        Editora editora = toEntity(dto);
        Editora criada = editoraService.salvar(editora);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(criada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraResponseDTO> atualizar(@PathVariable Long id, 
                                                       @Valid @RequestBody EditoraRequestDTO dto) {
        Editora existente = editoraService.buscarPorIdOuLancarExcecao(id);
        existente.setNome(dto.getNome());
        existente.setCidade(dto.getCidade());
        Editora atualizada = editoraService.salvar(existente);
        return ResponseEntity.ok(toResponseDTO(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        editoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EditoraResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<Editora> editoras = editoraService.buscarPorNome(nome);
        List<EditoraResponseDTO> response = editoras.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<EditoraResponseDTO>> buscarPorCidade(@PathVariable String cidade) {
        List<Editora> editoras = editoraService.buscarPorCidade(cidade);
        List<EditoraResponseDTO> response = editoras.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Métodos auxiliares para conversão
    private EditoraResponseDTO toResponseDTO(Editora editora) {
        return new EditoraResponseDTO(editora.getId(), editora.getNome(), editora.getCidade());
    }

    private Editora toEntity(EditoraRequestDTO dto) {
        return new Editora(dto.getNome(), dto.getCidade());
    }
} 