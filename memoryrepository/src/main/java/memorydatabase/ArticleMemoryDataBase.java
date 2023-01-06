package memorydatabase;

import alcuk.domain.article.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ArticleMemoryDataBase {
    public final static List<Article>articles;
    public final static AtomicInteger autoIncrementId;
    static{
        articles=new ArrayList<>();
        autoIncrementId=new AtomicInteger(1);
    }
}
