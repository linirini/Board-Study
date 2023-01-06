package alcuk.user.service.spi;

import alcuk.user.service.dto.UserDto;

public interface UserServiceRepository {

    void createUser(UserDto userDto);

    UserDto readUser(String name);

    void deleteUser(int id);

}
