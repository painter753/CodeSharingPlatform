package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.model.CodeSnippet;
import platform.service.CodeSnippetsService;

@Controller
public class WebController {

    private CodeSnippetsService codeRepository;

    public WebController(CodeSnippetsService codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/code/{id}")
    public String getCodeByNumber(@PathVariable String id, Model model) {
        CodeSnippet snippet = codeRepository.getSnippetByNumber(id);
        if (snippet == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("snippet", snippet);
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getCodeLatest(Model model) {
        model.addAttribute("snippets", codeRepository.getSnippetLatest());
        return "snippets";
    }

    @GetMapping("/code/new")
    public String getCodeForm() {
        return "new_snippet";
    }

}
