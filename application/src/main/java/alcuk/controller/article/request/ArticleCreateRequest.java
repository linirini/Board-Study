package alcuk.controller.article.request;

public class ArticleCreateRequest {

    private String title;
    private String content;
    private AuthorCreateRequest authorCreateRequest;

    public ArticleCreateRequest(){}
    public ArticleCreateRequest(String title, String content,AuthorCreateRequest authorCreateRequest){
        this.title=title;
        this.content=content;
        this.authorCreateRequest=authorCreateRequest;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public AuthorCreateRequest getAuthorCreateRequest(){ return authorCreateRequest; }
}
