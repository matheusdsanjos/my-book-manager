package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.model.Editora;
import br.edu.ifsp.demo.my_book_manager.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/editoras")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Gerenciamento de Editoras");
        model.addAttribute("editoras", editoraService.listarTodos());
        return "editora/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("title", "Nova Editora");
        model.addAttribute("editora", new Editora());
        return "editora/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("editora") Editora editora,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", editora.getId() != null ? "Editar Editora" : "Nova Editora");
            return "editora/form";
        }
        
        try {
            editoraService.salvar(editora);
            redirectAttributes.addFlashAttribute("mensagem", "Editora salva com sucesso!");
            return "redirect:/editoras";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar editora: " + e.getMessage());
            model.addAttribute("title", editora.getId() != null ? "Editar Editora" : "Nova Editora");
            return "editora/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Editora editora = editoraService.buscarPorIdOuLancarExcecao(id);
            model.addAttribute("title", "Editar Editora");
            model.addAttribute("editora", editora);
            return "editora/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Editora não encontrada: " + e.getMessage());
            return "redirect:/editoras";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            editoraService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Editora excluída com sucesso!");
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("foreign key constraint fails")) {
                redirectAttributes.addFlashAttribute("erro", "Não é possível excluir esta editora pois existem livros vinculados a ela. Remova primeiro os livros associados.");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Erro ao excluir editora: " + e.getMessage());
            }
        }
        return "redirect:/editoras";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String nome, Model model) {
        model.addAttribute("title", "Buscar Editoras");
        if (nome != null && !nome.trim().isEmpty()) {
            model.addAttribute("editoras", editoraService.buscarPorNome(nome));
            model.addAttribute("termoBusca", nome);
        } else {
            model.addAttribute("editoras", editoraService.listarTodos());
        }
        return "editora/list";
    }
} 