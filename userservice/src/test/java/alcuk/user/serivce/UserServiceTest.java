package alcuk.user.serivce;

import alcuk.user.service.DefaultUserService;
import alcuk.user.service.UserService;
import alcuk.user.service.dto.UserDto;
import alcuk.user.service.spi.UserServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DefaultUserService.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserServiceRepository userServiceRepository;

    @Test
    @DisplayName("유저 생성 및 조회")
    public void CREATE_AND_READ_USER(){
        //given
        String name = "linirini";
        String userId = "linirini_id";
        String userPassWord = "linirini_pwd";
        UserDto createRequest = new UserDto(name, userId, userPassWord);

        //when
        Mockito.when(userServiceRepository.readUser(Mockito.anyString())).thenReturn(createRequest);
        userService.createUser(createRequest);
        UserDto result = userService.readUser(name);

        //then
        Assertions.assertAll(
                ()-> Assertions.assertEquals(name, result.getName()),
                ()-> Assertions.assertEquals(userId, result.getUserId()),
                ()-> Assertions.assertEquals(userPassWord, result.getUserPassword())
        );
    }

    @Test
    @DisplayName("유저 삭제")
    public void DELETE_USER(){
        // given
        int id = 1;

        // then
        Assertions.assertDoesNotThrow(()-> userService.deleteUser(id));
    }

}
