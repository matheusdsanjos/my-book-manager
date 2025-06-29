package br.edu.ifsp.demo.my_book_manager.controller;

import br.edu.ifsp.demo.my_book_manager.model.Livro;
import br.edu.ifsp.demo.my_book_manager.service.AutorService;
import br.edu.ifsp.demo.my_book_manager.service.EditoraService;
import br.edu.ifsp.demo.my_book_manager.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final AutorService autorService;
    private final EditoraService editoraService;

    public LivroController(LivroService livroService, AutorService autorService, EditoraService editoraService) {
        this.livroService = livroService;
        this.autorService = autorService;
        this.editoraService = editoraService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("title", "Gerenciamento de Livros");
        model.addAttribute("livros", livroService.listarTodos());
        return "livro/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("title", "Novo Livro");
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorService.listarTodos());
        model.addAttribute("editoras", editoraService.listarTodos());
        return "livro/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("livro") Livro livro,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", livro.getId() != null ? "Editar Livro" : "Novo Livro");
            model.addAttribute("autores", autorService.listarTodos());
            model.addAttribute("editoras", editoraService.listarTodos());
            return "livro/form";
        }
        
        try {
            livroService.salvar(livro);
            redirectAttributes.addFlashAttribute("mensagem", "Livro salvo com sucesso!");
            return "redirect:/livros";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar livro: " + e.getMessage());
            model.addAttribute("title", livro.getId() != null ? "Editar Livro" : "Novo Livro");
            model.addAttribute("autores", autorService.listarTodos());
            model.addAttribute("editoras", editoraService.listarTodos());
            return "livro/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Livro livro = livroService.buscarPorIdOuLancarExcecao(id);
            model.addAttribute("title", "Editar Livro");
            model.addAttribute("livro", livro);
            model.addAttribute("autores", autorService.listarTodos());
            model.addAttribute("editoras", editoraService.listarTodos());
            return "livro/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Livro não encontrado: " + e.getMessage());
            return "redirect:/livros";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            livroService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Livro excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir livro: " + e.getMessage());
        }
        return "redirect:/livros";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String termo,
                         @RequestParam(required = false) Long autorId,
                         @RequestParam(required = false) Long editoraId,
                         @RequestParam(required = false) Integer ano,
                         @RequestParam(required = false) BigDecimal precoMin,
                         @RequestParam(required = false) BigDecimal precoMax,
                         Model model) {
        
        model.addAttribute("title", "Buscar Livros");
        
        if (termo != null && !termo.trim().isEmpty()) {
            model.addAttribute("livros", livroService.buscarPorTituloAutorOuIsbn(termo));
            model.addAttribute("termoBusca", termo);
        } else if (autorId != null) {
            model.addAttribute("livros", livroService.buscarPorAutor(autorId));
            model.addAttribute("autorSelecionado", autorService.buscarPorId(autorId).orElse(null));
        } else if (editoraId != null) {
            model.addAttribute("livros", livroService.buscarPorEditora(editoraId));
            model.addAttribute("editoraSelecionada", editoraService.buscarPorId(editoraId).orElse(null));
        } else if (ano != null) {
            model.addAttribute("livros", livroService.buscarPorAnoPublicacao(ano));
            model.addAttribute("anoSelecionado", ano);
        } else if (precoMin != null && precoMax != null) {
            model.addAttribute("livros", livroService.buscarPorFaixaPreco(precoMin, precoMax));
            model.addAttribute("precoMin", precoMin);
            model.addAttribute("precoMax", precoMax);
        } else {
            model.addAttribute("livros", livroService.listarTodos());
        }
        
        model.addAttribute("autores", autorService.listarTodos());
        model.addAttribute("editoras", editoraService.listarTodos());
        return "livro/list";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Livro livro = livroService.buscarPorIdOuLancarExcecao(id);
            model.addAttribute("title", "Detalhes do Livro");
            model.addAttribute("livro", livro);
            return "livro/view";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Livro não encontrado: " + e.getMessage());
            return "redirect:/livros";
        }
    }
} 