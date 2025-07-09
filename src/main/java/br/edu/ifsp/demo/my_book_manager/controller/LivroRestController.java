package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.dto.LivroRequestDTO;
import br.edu.ifsp.demo.my_book_manager.dto.LivroResponseDTO;
import br.edu.ifsp.demo.my_book_manager.model.Livro;
import br.edu.ifsp.demo.my_book_manager.service.AutorService;
import br.edu.ifsp.demo.my_book_manager.service.EditoraService;
import br.edu.ifsp.demo.my_book_manager.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/livros")
public class LivroRestController {

    private final LivroService livroService;
    private final AutorService autorService;
    private final EditoraService editoraService;

    public LivroRestController(LivroService livroService, AutorService autorService, EditoraService editoraService) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.editoraService = editoraService;
    }

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listar() {
        List<Livro> livros = livroService.listarTodos();
        List<LivroResponseDTO> response = livros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> obterPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarPorIdOuLancarExcecao(id);
        return ResponseEntity.ok(toResponseDTO(livro));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@Valid @RequestBody LivroRequestDTO dto) {
        Livro livro = toEntity(dto);
        Livro criado = livroService.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(@PathVariable Long id, 
                                                     @Valid @RequestBody LivroRequestDTO dto) {
        Livro existente = livroService.buscarPorIdOuLancarExcecao(id);
        existente.setTitulo(dto.getTitulo());
        existente.setIsbn(dto.getIsbn());
        existente.setAnoPublicacao(dto.getAnoPublicacao());
        existente.setPreco(dto.getPreco());
        existente.setAutor(autorService.buscarPorIdOuLancarExcecao(dto.getAutorId()));
        existente.setEditora(editoraService.buscarPorIdOuLancarExcecao(dto.getEditoraId()));
        Livro atualizado = livroService.salvar(existente);
        return ResponseEntity.ok(toResponseDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorTermo(@RequestParam String termo) {
        List<Livro> livros = livroService.buscarPorTituloAutorOuIsbn(termo);
        List<LivroResponseDTO> response = livros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorAutor(@PathVariable Long autorId) {
        List<Livro> livros = livroService.buscarPorAutor(autorId);
        List<LivroResponseDTO> response = livros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/editora/{editoraId}")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorEditora(@PathVariable Long editoraId) {
        List<Livro> livros = livroService.buscarPorEditora(editoraId);
        List<LivroResponseDTO> response = livros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorAno(@PathVariable Integer ano) {
        List<Livro> livros = livroService.buscarPorAnoPublicacao(ano);
        List<LivroResponseDTO> response = livros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/preco")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorFaixaPreco(
            @RequestParam BigDecimal precoMin, 
            @RequestParam BigDecimal precoMax) {
        List<Livro> livros = livroService.buscarPorFaixaPreco(precoMin, precoMax);
        List<LivroResponseDTO> response = livros.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroResponseDTO> buscarPorIsbn(@PathVariable String isbn) {
        Livro livro = livroService.buscarPorIsbn(isbn);
        if (livro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toResponseDTO(livro));
    }

    // Métodos auxiliares para conversão
    private LivroResponseDTO toResponseDTO(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getAnoPublicacao(),
                livro.getPreco(),
                livro.getAutor().getId(),
                livro.getAutor().getNome(),
                livro.getEditora().getId(),
                livro.getEditora().getNome()
        );
    }

    private Livro toEntity(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setPreco(dto.getPreco());
        livro.setAutor(autorService.buscarPorIdOuLancarExcecao(dto.getAutorId()));
        livro.setEditora(editoraService.buscarPorIdOuLancarExcecao(dto.getEditoraId()));
        return livro;
    }
} 