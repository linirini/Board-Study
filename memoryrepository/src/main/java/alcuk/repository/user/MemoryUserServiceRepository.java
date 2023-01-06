package alcuk.repository.user;

import alcuk.domain.user.User;
import alcuk.user.service.dto.UserDto;
import alcuk.user.service.spi.UserServiceRepository;
import memorydatabase.UserMemoryDataBase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemoryUserServiceRepository implements UserServiceRepository {

    @Override
    public void createUser(UserDto userDto) {
        User user = new User(UserMemoryDataBase.autoIncrementId.getAndIncrement(),
                userDto.getName(),
                userDto.getUserId(),
                userDto.getUserPassword(),
                LocalDate.now());

        UserMemoryDataBase.users.add(user);
    }

    @Override
    public UserDto readUser(String name) {
        for(User user : UserMemoryDataBase.users){
            if(user.getName().equals(name))
                return new UserDto(user.getId(),
                        user.getName(),
                        user.getUserId(),
                        user.getUserPassword(),
                        user.getCreatedAt());
        }
        return null;
    }

    @Override
    public void deleteUser(int id) {
        for(User user : UserMemoryDataBase.users){
            if(user.getId() == id){
                UserMemoryDataBase.users.remove(user);
                return;
            }
        }
    }

    public void deleteAll(){
        UserMemoryDataBase.users.clear();
    }

}
