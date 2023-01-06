package alcuk.user.service;

import alcuk.user.service.dto.UserDto;
import alcuk.user.service.spi.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{

    private final UserServiceRepository userServiceRepository;

    @Autowired
    public DefaultUserService(UserServiceRepository userServiceRepository) {
        this.userServiceRepository = userServiceRepository;
    }

    @Override
    public void createUser(UserDto userDto) {
        userServiceRepository.createUser(userDto);
    }

    @Override
    public UserDto readUser(String name) {
        return userServiceRepository.readUser(name);
    }

    @Override
    public void deleteUser(int id) {
        userServiceRepository.deleteUser(id);
    }

}
