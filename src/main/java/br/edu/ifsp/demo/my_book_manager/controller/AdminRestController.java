package br.edu.ifsp.demo.my_book_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @GetMapping
    public String adminEndpoint() {
        return "Acesso concedido: você é um administrador! Bem-vindo à área administrativa.";
    }
} 