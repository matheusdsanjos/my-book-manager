package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.model.Autor;
import br.edu.ifsp.demo.my_book_manager.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Gerenciamento de Autores");
        model.addAttribute("autores", autorService.listarTodos());
        return "autor/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("title", "Novo Autor");
        model.addAttribute("autor", new Autor());
        return "autor/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("autor") Autor autor,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", autor.getId() != null ? "Editar Autor" : "Novo Autor");
            return "autor/form";
        }
        
        try {
            autorService.salvar(autor);
            redirectAttributes.addFlashAttribute("mensagem", "Autor salvo com sucesso!");
            return "redirect:/autores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar autor: " + e.getMessage());
            model.addAttribute("title", autor.getId() != null ? "Editar Autor" : "Novo Autor");
            return "autor/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Autor autor = autorService.buscarPorIdOuLancarExcecao(id);
            model.addAttribute("title", "Editar Autor");
            model.addAttribute("autor", autor);
            return "autor/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Autor não encontrado: " + e.getMessage());
            return "redirect:/autores";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            autorService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Autor excluído com sucesso!");
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("foreign key constraint fails")) {
                redirectAttributes.addFlashAttribute("erro", "Não é possível excluir este autor pois existem livros vinculados a ele. Remova primeiro os livros associados.");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Erro ao excluir autor: " + e.getMessage());
            }
        }
        return "redirect:/autores";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String nome, Model model) {
        model.addAttribute("title", "Buscar Autores");
        if (nome != null && !nome.trim().isEmpty()) {
            model.addAttribute("autores", autorService.buscarPorNome(nome));
            model.addAttribute("termoBusca", nome);
        } else {
            model.addAttribute("autores", autorService.listarTodos());
        }
        return "autor/list";
    }
} 