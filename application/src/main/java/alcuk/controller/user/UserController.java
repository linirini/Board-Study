package alcuk.controller.user;

import alcuk.controller.user.request.UserCreateRequest;
import alcuk.controller.user.response.UserResponse;
import alcuk.user.service.UserService;
import alcuk.user.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{name}")
    public UserResponse readUser(@PathVariable("name") String name){
        UserDto userDto = userService.readUser(name);
        return new UserResponse(userDto.getId()
                , userDto.getName()
                , userDto.getUserId()
                , userDto.getUserPassword()
                , userDto.getCreatedAt());
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UserCreateRequest createRequest){
        UserDto userDto = new UserDto(-1, createRequest.getName(), createRequest.getUserId(), createRequest.getUserPassword(), null);
        userService.createUser(userDto);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
}
