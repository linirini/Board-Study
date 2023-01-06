package alcuk.acceptance.article;

import alcuk.controller.article.request.ArticleCreateRequest;
import alcuk.controller.article.request.AuthorCreateRequest;
import alcuk.controller.article.response.ArticleResponse;
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
public class ArticleAcceptanceTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final List<ArticleResponse> articles;
    {
        articles =new ArrayList<>();
    }

    private final List<UserResponse> users;
    {
        users =new ArrayList<>();
    }

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_USERS() throws Exception{
        for(UserResponse deleteWaitUser : users)
            mvc.perform(MockMvcRequestBuilders.delete("/user/{id}",deleteWaitUser.getId()));
        throwIfDoesNotDeletedAllUsers();
    }
    private void throwIfDoesNotDeletedAllUsers(){
        for(UserResponse deleteWaitUser : users){
            Assertions.assertThrows(NestedServletException.class,()->
                    mvc.perform(MockMvcRequestBuilders.get("/user/{name}",deleteWaitUser.getName()))
                            .andExpectAll(
                                    MockMvcResultMatchers.status().isOk(),
                                    MockMvcResultMatchers.content().string("")
                            )
            );
        }
    }
    @BeforeEach
    @AfterEach
    public void DELETE_ALL_ARTICLES() throws Exception{
        for(ArticleResponse deleteWaitArticle : articles)
            mvc.perform(MockMvcRequestBuilders.delete("/article/{id}",deleteWaitArticle.getId()));
        throwIfDoesNotDeletedAllArticles();
    }

    private void throwIfDoesNotDeletedAllArticles() throws Exception{
        for(ArticleResponse deleteWaitArticle: articles){
            Assertions.assertThrows(AssertionError.class,()->
                    mvc.perform(MockMvcRequestBuilders.get("/article"))
                            .andExpectAll(
                                    MockMvcResultMatchers.status().isOk(),
                                    MockMvcResultMatchers.content().string("")
                            ).andDo(MockMvcResultHandlers.print())

            );
        }
    }

    @Test
    @DisplayName("게시판 생성 성공 테스트")
    public void CREATE_NEW_ARTICLE_SUCCESS_TEST() throws Exception {
        //given
        String urlArticle="/article";
        String urlUser="/user";
        UserCreateRequest requestUser=new UserCreateRequest("linirini","linirini_id","linirini_pwd");
        mvc.perform(MockMvcRequestBuilders.post(urlUser).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestUser)));
        ResultActions resultUser=mvc.perform(MockMvcRequestBuilders.get("/user/{name}",requestUser.getName()));
        UserResponse deleteWaitUser=objectMapper.readValue(resultUser.andReturn().getResponse().getContentAsString(),UserResponse.class);
        users.add(deleteWaitUser);
        ArticleCreateRequest requestArticle=new ArticleCreateRequest("HelloWorld","abc",new AuthorCreateRequest(1,"linirini"));

        //when
        mvc.perform(MockMvcRequestBuilders.post(urlArticle).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestArticle)));
        ResultActions result=mvc.perform(MockMvcRequestBuilders.get("/article"));
        ArticleResponse[] deleteWaitArticle=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),ArticleResponse[].class);
        articles.add(deleteWaitArticle[0]);

        //then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.[0].title").value(requestArticle.getTitle()),
                MockMvcResultMatchers.jsonPath("$.[0].content").value(requestArticle.getContent()),
                MockMvcResultMatchers.jsonPath("$.[0].author.id").value(requestArticle.getAuthorCreateRequest().getId()),
                MockMvcResultMatchers.jsonPath("$.[0].author.name").value(requestArticle.getAuthorCreateRequest().getName())
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("게시판 조회 성공 테스트")
    public void READ_ARTICLES_SUCCESS_TEST() throws Exception{
        //given
        String saveUrl="/article";
        String readUrl="/article";
        UserCreateRequest requestUser1=new UserCreateRequest("linirini","linirini_id","linirini_pwd");
        mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestUser1)));
        ResultActions resultUser1=mvc.perform(MockMvcRequestBuilders.get("/user/{name}",requestUser1.getName()));
        UserResponse userResponse1=objectMapper.readValue(resultUser1.andReturn().getResponse().getContentAsString(),UserResponse.class);
        users.add(userResponse1);
        UserCreateRequest requestUser2=new UserCreateRequest("linirini 2","linirini_id 2","linirini_pwd 2");
        mvc.perform(MockMvcRequestBuilders.post("/user").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestUser2)));
        ResultActions resultUser2=mvc.perform(MockMvcRequestBuilders.get("/user/{name}",requestUser2.getName()));
        UserResponse userResponse2=objectMapper.readValue(resultUser2.andReturn().getResponse().getContentAsString(),UserResponse.class);
        users.add(userResponse2);
        ArticleCreateRequest request1=new ArticleCreateRequest("hello world","abc",new AuthorCreateRequest(userResponse1.getId(),userResponse1.getName()));
        ArticleCreateRequest request2=new ArticleCreateRequest("hello world 2","abc 2",new AuthorCreateRequest(userResponse2.getId(),userResponse2.getName()));

        //when
        mvc.perform(MockMvcRequestBuilders.post(saveUrl).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request1)));
        mvc.perform(MockMvcRequestBuilders.post(saveUrl).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request2)));
        ResultActions result=mvc.perform(MockMvcRequestBuilders.get(readUrl));
        ArticleResponse[] articles=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(),ArticleResponse[].class);
        this.articles.add(articles[0]);
        this.articles.add(articles[1]);

        //then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$[0].title").value(request1.getTitle()),
                MockMvcResultMatchers.jsonPath("$[0].content").value(request1.getContent()),
                MockMvcResultMatchers.jsonPath("$.[0].author.id").value(request1.getAuthorCreateRequest().getId()),
                MockMvcResultMatchers.jsonPath("$.[0].author.name").value(request1.getAuthorCreateRequest().getName()),

                MockMvcResultMatchers.jsonPath("$[1].title").value(request2.getTitle()),
                MockMvcResultMatchers.jsonPath("$[1].content").value(request2.getContent()),
                MockMvcResultMatchers.jsonPath("$.[1].author.id").value(request2.getAuthorCreateRequest().getId()),
                MockMvcResultMatchers.jsonPath("$.[1].author.name").value(request2.getAuthorCreateRequest().getName())
        ).andDo(MockMvcResultHandlers.print());

    }
}
