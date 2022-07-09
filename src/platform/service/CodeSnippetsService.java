package platform.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import platform.model.CodeSnippet;
import platform.repository.CodeRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
//TODO need to refactor
public class CodeSnippetsService {

    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
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
                .filter(sn -> !sn.isRestricted())
                .limit(10)
                .collect(Collectors.toList());
    }

    public CodeSnippet getSnippetByNumber(String id) {
        CodeSnippet snippet = codeRepository.findById(id).orElse(null);

        if (snippet != null && snippet.isRestricted()) {
            String[] time = snippet.getTimestampCreated().split(" ");
            LocalDateTime created = LocalDateTime.of(LocalDate.parse(time[0]), LocalTime.parse(time[1]));
            long interval = Duration.between(created, LocalDateTime.now().withNano(0)).getSeconds();
            int seconds = snippet.getSeconds();
            if (snippet.hasTimeRestriction()) {
                if (interval > seconds) {
                    codeRepository.delete(snippet);
                    return null;
                } else {
                    int timeRemaining = (int) (seconds - interval);
                    snippet.setSeconds(timeRemaining);
                }
            }

            int views = snippet.getViews();
            snippet.setViews(snippet.getViews() - 1);
            if (views > 1) {
                codeRepository.save(snippet);
                return snippet;
            }
            if (views == 1) {
                codeRepository.delete(snippet);
                return snippet;
            }
        }
        return snippet;
    }

    public String createNewSnippet(String code, int timeRestriction, int viewsRestriction) {
        CodeSnippet snippet = new CodeSnippet(code, getTimestamp(), viewsRestriction, timeRestriction);
        return codeRepository.save(snippet).getId();
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMATTER));
    }


}
