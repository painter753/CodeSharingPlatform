package platform.model;

import java.time.LocalDateTime;

public class CodeSnippet {

    private String code;
    private String timestamp;

    public CodeSnippet(String code, String timestamp) {
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
