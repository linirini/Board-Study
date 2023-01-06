package alcuk.article.service.dto;

public class AuthorDto {
    private int id;

    private String name;
    public AuthorDto(){}
    public AuthorDto(int id, String name) {
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
