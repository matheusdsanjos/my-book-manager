package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.dto.AutorRequestDTO;
import br.edu.ifsp.demo.my_book_manager.dto.AutorResponseDTO;
import br.edu.ifsp.demo.my_book_manager.model.Autor;
import br.edu.ifsp.demo.my_book_manager.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/autores")
public class AutorRestController {

    private final AutorService autorService;

    public AutorRestController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> listar() {
        List<Autor> autores = autorService.listarTodos();
        List<AutorResponseDTO> response = autores.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> obterPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorIdOuLancarExcecao(id);
        return ResponseEntity.ok(toResponseDTO(autor));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> criar(@Valid @RequestBody AutorRequestDTO dto) {
        Autor autor = toEntity(dto);
        Autor criado = autorService.salvar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> atualizar(@PathVariable Long id, 
                                                     @Valid @RequestBody AutorRequestDTO dto) {
        Autor existente = autorService.buscarPorIdOuLancarExcecao(id);
        existente.setNome(dto.getNome());
        existente.setNacionalidade(dto.getNacionalidade());
        Autor atualizado = autorService.salvar(existente);
        return ResponseEntity.ok(toResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AutorResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<Autor> autores = autorService.buscarPorNome(nome);
        List<AutorResponseDTO> response = autores.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nacionalidade/{nacionalidade}")
    public ResponseEntity<List<AutorResponseDTO>> buscarPorNacionalidade(@PathVariable String nacionalidade) {
        List<Autor> autores = autorService.buscarPorNacionalidade(nacionalidade);
        List<AutorResponseDTO> response = autores.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Métodos auxiliares para conversão
    private AutorResponseDTO toResponseDTO(Autor autor) {
        return new AutorResponseDTO(autor.getId(), autor.getNome(), autor.getNacionalidade());
    }

    private Autor toEntity(AutorRequestDTO dto) {
        return new Autor(dto.getNome(), dto.getNacionalidade());
    }
} 