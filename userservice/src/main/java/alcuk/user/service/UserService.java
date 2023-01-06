package alcuk.user.service;

import alcuk.user.service.dto.UserDto;

public interface UserService {

    void createUser(UserDto userDto);

    UserDto readUser(String name);

    void deleteUser(int id);

}
