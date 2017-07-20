package Service;

import model.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangw on 2017/7/20.
 */
@Service
public class UserService {

    private final ConcurrentHashMap<String, User> users;

    public UserService() {
        users = new ConcurrentHashMap<>();
    }

    public boolean addUser(User user) {
        boolean isExist = users.containsKey(user.getUsername());
        if (isExist) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public User getByUsername(String username) {
        return users.get(username);
    }

}
