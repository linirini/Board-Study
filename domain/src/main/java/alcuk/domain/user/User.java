package alcuk.domain.user;

import java.time.LocalDate;

public class User {

    private final int id;
    private final String name;
    private final  String userId;
    private final String userPassword;
    private final LocalDate createdAt;

    public User(int id, String name, String userId, String userPassword, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.userPassword = userPassword;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
