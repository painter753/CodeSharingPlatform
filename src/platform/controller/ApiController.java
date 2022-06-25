package platform.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private CodeRepository codeRepository;

    public ApiController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping("/code")
    public ResponseEntity<Map<String, String>> getCode() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        CodeSnippet snippet = codeRepository.getSnippet();
        if (snippet != null) {
            return ResponseEntity.ok().headers(httpHeaders).body(
                    Map.of(
                            "code", snippet.getCode(),
                            "date", snippet.getTimestamp().toString()
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/code/new")
    public ResponseEntity<Map<String, String>> postCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");
        codeRepository.addNewSnippet(new CodeSnippet(code));
        return ResponseEntity.ok(Map.of());
    }

}
