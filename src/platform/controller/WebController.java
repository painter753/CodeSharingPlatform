package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;

@Controller
public class WebController {

    private CodeRepository codeRepository;

    public WebController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/code/{id}")
    public String getCodeByNumber(@PathVariable int id, Model model) {
        CodeSnippet snippet = codeRepository.getSnippetByNumber(id);
        model.addAttribute("snippet", snippet);
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getCodeByNumber(Model model) {
        model.addAttribute("snippets", codeRepository.getSnippetLatest());
        return "snippets";
    }

    @GetMapping("/code/new")
    public String getCodeForm() {
        return "new_snippet";
    }

}
