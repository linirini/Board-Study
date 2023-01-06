package alcuk.repository.article;

import alcuk.article.service.dto.ArticleDto;
import alcuk.article.service.dto.AuthorDto;
import alcuk.article.service.spi.ArticleServiceRepository;
import alcuk.domain.article.Article;
import alcuk.domain.user.User;
import memorydatabase.ArticleMemoryDataBase;
import memorydatabase.UserMemoryDataBase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemoryArticleServiceRepository implements ArticleServiceRepository {

    @Override
    public void createArticle(ArticleDto articleDto) {
        User user = null;
        for(User element: UserMemoryDataBase.users){
            if(element.getId()==articleDto.getAuthorDto().getId()){
                user=new User(element.getId(),element.getName(),element.getUserId(),element.getUserPassword(),element.getCreatedAt());
            }
        }
        Article article = new Article(ArticleMemoryDataBase.autoIncrementId.getAndIncrement(),
                articleDto.getTitle(),
                articleDto.getContent(),
                user,
                LocalDate.now());
        ArticleMemoryDataBase.articles.add(article);
    }

    @Override
    public List<ArticleDto> readArticle() {
        List<ArticleDto>articles=new ArrayList<>();
        for(Article article:ArticleMemoryDataBase.articles){
            articles.add(new ArticleDto(article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    new AuthorDto(article.getUser().getId(), article.getUser().getName()),
                    article.getCreatedAt()));
        }
        return articles;
    }

    @Override
    public void deleteArticle(int id) {
        for(Article article:ArticleMemoryDataBase.articles){
            if(article.getId()==id){
                ArticleMemoryDataBase.articles.remove(article);
                return;
            }
        }
    }
    public void deleteAll(){ArticleMemoryDataBase.articles.clear();}
}
