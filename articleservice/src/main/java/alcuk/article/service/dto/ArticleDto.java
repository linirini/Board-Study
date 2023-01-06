package alcuk.article.service.dto;

import java.time.LocalDate;

public class ArticleDto{

    private int id;
    private String title;
    private String content;
    private AuthorDto authorDto;
    private LocalDate createdAt;

    public ArticleDto(String title, String content,AuthorDto authorDto){
        this.title=title;
        this.content=content;
        this.authorDto=authorDto;
    }

    public ArticleDto(int id, String title, String content, AuthorDto authorDto, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorDto = authorDto;
        this.createdAt = createdAt;
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

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public LocalDate getCreatedAt() { return createdAt; }
}
