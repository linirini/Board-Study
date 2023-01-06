package alcuk.controller.article.request;

public class AuthorCreateRequest {
    private int id;
    private String name;
    public AuthorCreateRequest(){}
    public AuthorCreateRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
