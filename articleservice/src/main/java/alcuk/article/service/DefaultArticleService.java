package alcuk.article.service;

import alcuk.article.service.dto.ArticleDto;
import alcuk.article.service.spi.ArticleServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultArticleService implements ArticleService{
    private final ArticleServiceRepository articleServiceRepository;

    public DefaultArticleService(ArticleServiceRepository articleServiceRepository) {
        this.articleServiceRepository = articleServiceRepository;
    }

    @Override
    public void createArticle(ArticleDto articleDto) {
        articleServiceRepository.createArticle(articleDto);
    }

    @Override
    public List<ArticleDto> readArticle() {
        return articleServiceRepository.readArticle();
    }

    @Override
    public void deleteArticle(int id) {
        articleServiceRepository.deleteArticle(id);
    }
}
