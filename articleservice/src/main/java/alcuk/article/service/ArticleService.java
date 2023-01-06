package alcuk.article.service;

import alcuk.article.service.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    void createArticle(ArticleDto articleDto);
    List<ArticleDto> readArticle();
    void deleteArticle(int id);
}
