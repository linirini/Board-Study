package alcuk.controller.article.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ArticleResponse{
    private int id;
    private String title;
    private String content;

    @JsonProperty("author")
    private AuthorResponse authorResponse;
    @JsonProperty("created_At")
    private LocalDate createdAt;

    public ArticleResponse(){
    }

    public ArticleResponse(int id, String title, String content, AuthorResponse authorResponse, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorResponse = authorResponse;
        this.createdAt=createdAt;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public AuthorResponse getAuthorResponse() {
        return authorResponse;
    }

    public LocalDate getCreatedAt() { return createdAt; }
}