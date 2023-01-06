package alcuk.controller.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserResponse {

    private int id;
    private String name;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_password")
    private String userPassword;
    @JsonProperty("created_at")
    private LocalDate createdAt;

    public UserResponse(){}

    public UserResponse(int id, String name, String userId, String userPassword, LocalDate createdAt) {
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
