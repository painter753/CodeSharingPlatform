package platform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeSnippet;
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

    private CodeSnippetsService codeSnippetsService;

    public ApiController(CodeSnippetsService codeSnippetsService) {
        this.codeSnippetsService = codeSnippetsService;
    }

    @GetMapping("/code/{n}")
    public ResponseEntity<Object> getSnippetByNumber(@PathVariable String n) {
        CodeSnippet snippet = codeSnippetsService.getSnippetByNumber(n);;
        if (snippet != null) {
            return ResponseEntity.ok().body(
                    Map.of(
                            "time", snippet.getSeconds() > 0 ? snippet.getSeconds() : 0,
                            "views", snippet.getViews() > 0 ? snippet.getViews() : 0,
                            "code", snippet.getCode(),
                            "date", snippet.getTimestampCreated()
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/code/latest")
    public ResponseEntity<Object> getCode() {
        List<Map<String, ? extends Serializable>> snippets =
                codeSnippetsService.getSnippetLatest()
                        .stream()
                        .map(s ->
                                Map.of(
                                "code", s.getCode(),
                                "date", s.getTimestampCreated(),
                                "time", s.getSeconds(),
                                "views", s.getViews()
                                )
                        ).collect(Collectors.toList());
        return ResponseEntity.ok().body(snippets);
    }

    @PostMapping("/code/new")
    public ResponseEntity<Map<String, String>> postCode(@RequestBody Map<String, Object> request) {
        String code = String.valueOf(request.get("code"));
        int time = Integer.parseInt(request.get("time").toString());
        int views = Integer.parseInt(request.get("views").toString());
        String id = codeSnippetsService.createNewSnippet(code, time, views);
        return ResponseEntity.ok(Map.of("id", id));
    }


}
