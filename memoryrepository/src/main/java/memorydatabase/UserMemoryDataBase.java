package memorydatabase;

import alcuk.domain.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMemoryDataBase {

    public final static List<User> users;
    public final static AtomicInteger autoIncrementId;
    static {
        users = new ArrayList<>();
        autoIncrementId = new AtomicInteger(1);
    }

}
