package platform.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CodeController {

    @GetMapping("/code")
    public ResponseEntity<String> getCode() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "text/html");
        return ResponseEntity.ok().headers(httpHeaders).body(getFileContent());
    }

    @GetMapping("/api/code")
    public ResponseEntity<Map<String, String>> getApiCode() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity.ok().headers(httpHeaders).body(
                Map.of("code", "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}")
        );
    }

    private String getFileContent() {
        try {
            return Files.readAllLines(new ClassPathResource("static/index.html").getFile().toPath()).stream().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
