package alcuk.domain.article;

import alcuk.domain.user.User;

import java.time.LocalDate;

public class Article {


    private final int id;
    private final String title;
    private final String content;
    private final User user;
    private final LocalDate createdAt;

    public Article(int id, String title, String content, User user, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

}
