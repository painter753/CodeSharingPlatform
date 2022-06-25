package platform.model;

import java.time.LocalDateTime;

public class CodeSnippet {

    private String code;
    private LocalDateTime timestamp;

    public CodeSnippet(String code) {
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
