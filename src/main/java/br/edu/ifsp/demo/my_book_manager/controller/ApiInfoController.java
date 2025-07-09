package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.service.AutorService;
import br.edu.ifsp.demo.my_book_manager.service.EditoraService;
import br.edu.ifsp.demo.my_book_manager.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiInfoController {

    private final AutorService autorService;
    private final EditoraService editoraService;
    private final LivroService livroService;

    public ApiInfoController(AutorService autorService, EditoraService editoraService, LivroService livroService) {
        this.autorService = autorService;
        this.editoraService = editoraService;
        this.livroService = livroService;
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getApiInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("nome", "My Book Manager API");
        info.put("versao", "1.0.0");
        info.put("descricao", "API REST para gerenciamento de livros, autores e editoras");
        info.put("endpoints", Map.of(
                "autores", "/api/autores",
                "editoras", "/api/editoras",
                "livros", "/api/livros"
        ));
        return ResponseEntity.ok(info);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalAutores", autorService.listarTodos().size());
        stats.put("totalEditoras", editoraService.listarTodos().size());
        stats.put("totalLivros", livroService.listarTodos().size());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("message", "API está funcionando normalmente");
        return ResponseEntity.ok(health);
    }
} 