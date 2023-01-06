package alcuk.user.service.dto;

import java.time.LocalDate;

public class UserDto{
    private int id;
    private String name;
    private String userId;
    private String userPassword;
    private LocalDate createdAt;

    public UserDto(String name, String userId, String userPassword) {
        this.name = name;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public UserDto(int id, String name, String userId, String userPassword, LocalDate createdAt) {
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