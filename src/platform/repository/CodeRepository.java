package platform.repository;

import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CodeRepository {

    private List<CodeSnippet> codeSnippets;

    public CodeRepository() {
        this.codeSnippets = new ArrayList<>();
        codeSnippets.add(new CodeSnippet("public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}"));
    }

    public CodeSnippet getSnippet() {
        return codeSnippets.stream().max(Comparator.comparing(CodeSnippet::getTimestamp)).orElseGet(null);
    }

    public void addNewSnippet(CodeSnippet snippet) {
        codeSnippets.add(snippet);
    }


}
