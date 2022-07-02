package platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;
import platform.service.CodeSnippetsService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class ApiController {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";

    private CodeSnippetsService codeRepository;

    public ApiController(CodeSnippetsService codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/code/{n}")
    public ResponseEntity<Object> getSnippetByNumber(@PathVariable int n) {
        CodeSnippet snippet = codeRepository.getSnippetByNumber(n);
        return ResponseEntity.ok().body(
                Map.of(
                        "code", snippet.getCode(),
                        "date", snippet.getTimestamp()
                )
        );
    }

    @GetMapping("/code/latest")
    public ResponseEntity<Object> getCode() {
        List<Map<String, ? extends Serializable>> snippets =
                codeRepository.getSnippetLatest()
                        .stream()
                        .map(s ->
                                Map.of(
                                "code", s.getCode(),
                                "date", s.getTimestamp()
                                )
                        ).collect(Collectors.toList());
        return ResponseEntity.ok().body(snippets);
    }

    @PostMapping("/code/new")
    public ResponseEntity<Object> postCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        int id = codeRepository.addNewSnippet(new CodeSnippet(code, getTimestamp()));
        return ResponseEntity.ok(Map.of("id", id + ""));
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
    }

}
