package alcuk.article.service.spi;

import alcuk.article.service.dto.ArticleDto;

import java.util.List;

public interface ArticleServiceRepository {
    void createArticle(ArticleDto articleDto);
    List<ArticleDto> readArticle();
    void deleteArticle(int id);
}
