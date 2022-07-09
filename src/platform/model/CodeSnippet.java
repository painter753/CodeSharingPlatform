package platform.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "snippets")
@Getter
@Setter
@ToString
public class CodeSnippet {

    @Id
    @Setter(AccessLevel.NONE)
    private final String id;

    @Column(name = "code")
    private String code;

    @Column(name = "timestamp")
    private String timestampCreated;

    @Column(name = "views_to_expire")
    private int views;

    @Column(name = "time_to_expire")
    private int seconds;

    @Column(name = "restriction")
    @Setter(AccessLevel.NONE)
    private SnippetRestriction restriction;

    public CodeSnippet() {
        this.id = UUID.randomUUID().toString();
        this.restriction = SnippetRestriction.NONE;
    }

    public CodeSnippet(String code, String timestampCreated, int views, int seconds) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.timestampCreated = timestampCreated;
        this.views = views > 0 ? views : 0;
        this.seconds = seconds> 0 ? seconds : 0;
        this.restriction = views > 0 && seconds > 0 ?
                SnippetRestriction.BOTH : views > 0 ?
                SnippetRestriction.BY_VIEWS : seconds > 0 ?
                SnippetRestriction.BY_TIME : SnippetRestriction.NONE;
    }

    public String getId() {
        return id;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isRestricted() {
        return restriction != SnippetRestriction.NONE;
    }

    public boolean hasTimeRestriction() {
        return restriction == SnippetRestriction.BY_TIME || restriction == SnippetRestriction.BOTH;
    }

    public boolean hasViewsRestriction() {
        return restriction == SnippetRestriction.BY_VIEWS || restriction == SnippetRestriction.BOTH;
    }
}
