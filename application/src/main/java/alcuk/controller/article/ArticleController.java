package alcuk.controller.article;

import alcuk.article.service.ArticleService;
import alcuk.article.service.dto.ArticleDto;
import alcuk.article.service.dto.AuthorDto;
import alcuk.controller.article.request.ArticleCreateRequest;
import alcuk.controller.article.response.ArticleResponse;
import alcuk.controller.article.response.AuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {
    private ArticleService articleService;

    @Autowired
    private ArticleController(ArticleService articleService){this.articleService=articleService;}

    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleCreateRequest createRequest){
        ArticleDto articleDto=new ArticleDto(createRequest.getTitle(),createRequest.getContent(),new AuthorDto(createRequest.getAuthorCreateRequest().getId(),createRequest.getAuthorCreateRequest().getName()));
        articleService.createArticle(articleDto);
    }

    @GetMapping("/article")
    public List<ArticleResponse> readArticle(){
        List<ArticleDto> articleDtos=articleService.readArticle();
        List<ArticleResponse>responses=new ArrayList<>();
        for(ArticleDto article:articleDtos){
            responses.add(new ArticleResponse(article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    new AuthorResponse(article.getAuthorDto().getId(),article.getAuthorDto().getName()),
                    article.getCreatedAt()
            ));
        }
        return responses;
    }

    @DeleteMapping("/article/{id}")
    public void deleteArticle(@PathVariable("id")int id){
        articleService.deleteArticle(id);
    }
}
