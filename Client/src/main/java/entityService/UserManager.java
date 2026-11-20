package entityService;
import entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> users;

    private UserManager() {
        users = new HashMap<String, User>();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public boolean addUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }

        if (users.containsKey(user.getId())
                || isUsernameTaken(user.getUserName())) {
            return false;
        }

        users.put(user.getId(), user);
        return true;
    }

    public User getUser(String id) {
        return users.get(id);
    }

    public User getUserByUsername(String username) {
        if (username == null) {
            return null;
        }

        for (User u : users.values()) {
            if (u.getUserName().equalsIgnoreCase(username)) {
                return u;
            }
        }

        return null;
    }

    public List<User> getUsersByActive(boolean active) {
        List<User> list = new ArrayList<User>();
        for (User u : users.values()) {
            if (u.getStatus() == active) {
                list.add(u);
            }
        }
        return list;
    }

    public boolean isUsernameTaken(String username) {
        for (User user : users.values()) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailTaken(String email) {
        if (email == null) {
            return false;
        }

        for (User user : users.values()) {
            if (user.getEmail() != null
                    && user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public Map<String, User> getAllUser() {
        return Collections.unmodifiableMap(users);
    }

    public User remove(String id) {
        return users.remove(id);
    }
}
