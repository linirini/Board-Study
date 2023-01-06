package alcuk.controller.user.request;

import alcuk.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateRequest {

    private String name;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_password")
    private String userPassword;

    public UserCreateRequest(){}

    public UserCreateRequest(String name, String userId, String userPassword){
        this.name=name;
        this.userId=userId;
        this.userPassword=userPassword;
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
}
