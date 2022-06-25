package platform.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;

@RestController
public class WebController {

    private CodeRepository codeRepository;

    public WebController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(
            path = "/code",
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ResponseEntity<String> getCode() {
        return ResponseEntity.ok(getContent());
    }

    @GetMapping(
            path = "/code/new",
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ResponseEntity<String> getCodeForm() {
        return ResponseEntity.ok(getForm());
    }

    private String getContent() {
        CodeSnippet snippet = codeRepository.getSnippet();
        return "<html> <head> <title>Code</title> </head> <body>" +
                    "<pre id=\"code_snippet\"> " +
                        snippet.getCode() +
                    "</pre>" +
                    "<span id=\"load_date\">" +
                        snippet.getTimestamp().toString() +
                    "</span> </body>" +
                "</html>";
    }

    private String getForm() {
        return "<html> <head> <title>Create</title> </head> <body>" +
                "<textarea id=\"code_snippet\"></textarea>" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>" +
                "<script>" +
                "function send() {\n" +
                "    let object = {\n" +
                "        \"code\": document.getElementById(\"code_snippet\").value\n" +
                "    };\n" +
                "    \n" +
                "    let json = JSON.stringify(object);\n" +
                "    \n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    \n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                "}" +
                "</script>" +
                "</body>" +
                "</html>";
    }



}
