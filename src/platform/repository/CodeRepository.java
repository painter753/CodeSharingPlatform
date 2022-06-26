package platform.repository;

import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeRepository {

    private List<CodeSnippet> codeSnippets;

    public CodeRepository() {
        this.codeSnippets = new ArrayList<>();
    }

    public List<CodeSnippet> getSnippetLatest() {
        List<CodeSnippet> snippets = new ArrayList<>(codeSnippets);
        Collections.reverse(snippets);
        return snippets.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public CodeSnippet getSnippetByNumber(int n) {
        return codeSnippets.get(n - 1);
    }

    public int addNewSnippet(CodeSnippet snippet) {
        codeSnippets.add(snippet);
        return codeSnippets.size();
    }


}
