package alcuk.repository.article;

import alcuk.article.service.dto.ArticleDto;
import alcuk.article.service.dto.AuthorDto;
import alcuk.domain.user.User;
import memorydatabase.UserMemoryDataBase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={MemoryArticleServiceRepository.class})
public class MemoryArticleServiceRepositoryTest {
    @Autowired
    private MemoryArticleServiceRepository repository;

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_ARTICLES(){
        repository.deleteAll();
        UserMemoryDataBase.users.clear();
    }

    @Test
    @DisplayName("게시글 저장 및 조회 테스트")
    public void CREATE_AND_READ_ARTICLE_TEST(){
        //given
        ArticleDto articleDto = new ArticleDto("hello world","abc", new AuthorDto(1,"linirini"));
        User user = new User(1,"linirini","linirini_id","linirini_pwd", LocalDate.now());
        UserMemoryDataBase.users.add(user);

        //when
        repository.createArticle(articleDto);
        List<ArticleDto> result = repository.readArticle();

        //then
        Assertions.assertAll(
                ()->Assertions.assertEquals(articleDto.getTitle(),result.get(0).getTitle()),
                ()->Assertions.assertEquals(articleDto.getContent(),result.get(0).getContent())
        );
    }
    @Test
    @DisplayName("게시글 저장 및 삭제 테스트")
    public void CREATE_AND_DELETE_ARTICLE_TEST(){
        //given
        ArticleDto articleDto = new ArticleDto("hello world","abc",new AuthorDto(1,"linirini"));
        User user = new User(1,"linirini","linirini_id","linirini_pwd", LocalDate.now());
        UserMemoryDataBase.users.add(user);

        //when
        repository.createArticle(articleDto);
        List<ArticleDto> savedArticle=repository.readArticle();
        repository.deleteArticle(savedArticle.get(0).getId());
        List<ArticleDto> result = repository.readArticle();

        //then
        Assertions.assertEquals(result.isEmpty(),true);
    }
}
