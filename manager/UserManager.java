package manager;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private Map<String, User> userMap;
    private static UserManager instance = null;
    private UserManager(){
        this.userMap = new HashMap<>();
    }
    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    // Thêm tài khoản vào ds quản lý
    public boolean addUser(User user){
        if(user == null){
            return false;
        }
        if(userMap.containsKey(user.getId())){
            return false;
        }
        userMap.put(user.getId(), user);
        return true;
    }

    //Tìm tài khoản theo id
    public User getUserId(String id){
        return userMap.get(id);
    }

    //Tìm tài khoản theo Username
    public User getUserByUsername(String username){
        if(username == null || username.isBlank()){
            return null;
        }
        for(User u : userMap.values()){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    // danh sách tài khoản theo trạng thái
    public List<User> getUserByActive(boolean active){
        List<User> userList = new ArrayList<>();
        for(User u : userMap.values()){
            if(u.isActive() == active){
                userList.add(u);
            }
        }
        return List.copyOf(userList);
    }

    //In danh sách các tài khoản
    public Map<String, User> getAllUser(){
        return Map.copyOf(userMap);
    }

    //Xóa tài khoản
    public boolean removeUser(User user){
        if(user == null){
            return false;
        }
        return userMap.remove(user.getId(), user);
    }
}
