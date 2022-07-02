package platform.service;

import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CodeSnippetsService {

    private CodeRepository codeRepository;

    public CodeSnippetsService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public List<CodeSnippet> getSnippetLatest() {
        List<CodeSnippet> snippets = new ArrayList<>();


        for (CodeSnippet codeSnippet : codeRepository.findAll()) {
            snippets.add(codeSnippet);
        }

        Collections.reverse(snippets);
        return snippets.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public CodeSnippet getSnippetByNumber(int n) {
        return codeRepository.findById(n).orElseThrow(() -> new NoSuchElementException("No row found with id: " + n));
    }

    public int addNewSnippet(CodeSnippet snippet) {
        CodeSnippet save = codeRepository.save(snippet);
        return save.getId();
    }


}
