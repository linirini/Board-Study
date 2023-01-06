package alcuk.acceptance.user;

import alcuk.controller.user.request.UserCreateRequest;
import alcuk.controller.user.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserAcceptanceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final List<UserResponse> deleteWaitUsers;
    {
        deleteWaitUsers = new ArrayList<>();
    }

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_USER() throws Exception{
        for(UserResponse deleteWaitUser : deleteWaitUsers)
            mvc.perform(MockMvcRequestBuilders.delete("/user/{id}", deleteWaitUser.getId()));

        throwIfDoesNotDeletedAllUser();
    }

    private void throwIfDoesNotDeletedAllUser() throws Exception{
        for(UserResponse deleteWaitUser : deleteWaitUsers) {
            Assertions.assertThrows(NestedServletException.class, ()->
            mvc.perform(MockMvcRequestBuilders.get("/user/{name}", deleteWaitUser.getName()))
                    .andExpectAll(
                            MockMvcResultMatchers.status().isOk(),
                            MockMvcResultMatchers.content().string("")
                    )
            );
        }
    }

    @Test
    @DisplayName("유저 생성 성공 테스트")
    public void CREATE_NEW_USER_SUCCESS_TEST() throws Exception{
        // given
        String url = "/user";
        UserCreateRequest request = new UserCreateRequest("linirini", "linirini_id", "linirini_pwd");

        // when
        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/user/{name}", request.getName()));
        UserResponse deleteWaitUser = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), UserResponse.class);
        deleteWaitUsers.add(deleteWaitUser);

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.name").value(request.getName()),
                MockMvcResultMatchers.jsonPath("$.user_id").value(request.getUserId()),
                MockMvcResultMatchers.jsonPath("$.user_password").value(request.getUserPassword())
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("특정 유저 조회 테스트")
    public void READ_USER_SUCCESS_TEST() throws Exception{
        // given
        String saveUrl = "/user";
        String readUrl = "/user/{name}";
        UserCreateRequest userCreateRequest = new UserCreateRequest("linirini", "linirini_id", "linirini_pwd");

        // when
        mvc.perform(MockMvcRequestBuilders.post(saveUrl).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userCreateRequest)));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get(readUrl, userCreateRequest.getName()));

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.name").value(userCreateRequest.getName()),
                MockMvcResultMatchers.jsonPath("$.user_id").value(userCreateRequest.getUserId()),
                MockMvcResultMatchers.jsonPath("$.user_password").value(userCreateRequest.getUserPassword())
        ).andDo(MockMvcResultHandlers.print());
    }

}
