package alcuk.repository.user;

import alcuk.user.service.dto.UserDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MemoryUserServiceRepository.class})
public class MemoryUserServiceRepositoryTest {

    @Autowired
    private MemoryUserServiceRepository repository;

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_USER(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("유저 저장 및 조회 테스트")
    public void CREATE_AND_READ_USER_TEST(){
        // given
        UserDto userDto = new UserDto("linirini", "linirini_id", "linirini_pwd");

        // when
        repository.createUser(userDto);
        UserDto result = repository.readUser(userDto.getName());

        // then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(userDto.getName(), result.getName()),
                ()-> Assertions.assertEquals(userDto.getUserId(), result.getUserId()),
                ()-> Assertions.assertEquals(userDto.getUserPassword(), result.getUserPassword()),
                ()-> Assertions.assertDoesNotThrow(()-> userDto.getId())
        );
    }

    @Test
    @DisplayName("유저 저장 및 삭제 테스트")
    public void CREATE_AND_DELETE_USER_TEST(){
        // given
        UserDto userDto = new UserDto("linirini", "linirini_id", "linirini_pwd");

        // when
        repository.createUser(userDto);
        UserDto savedUser = repository.readUser(userDto.getName());
        repository.deleteUser(savedUser.getId());
        UserDto result = repository.readUser(userDto.getName());

        // then
        Assertions.assertNull(result);
    }

}
