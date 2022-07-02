package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface CodeRepository extends CrudRepository<CodeSnippet, Integer> {

    static final List<CodeSnippet> codeSnippets = new ArrayList<>();

    default List<CodeSnippet> getSnippetLatest() {
        List<CodeSnippet> snippets = new ArrayList<>(codeSnippets);
        Collections.reverse(snippets);
        return snippets.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    default CodeSnippet getSnippetByNumber(int n) {
        return codeSnippets.get(n - 1);
    }

    default int addNewSnippet(CodeSnippet snippet) {
        codeSnippets.add(snippet);
        return codeSnippets.size();
    }

}
