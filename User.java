import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class User {
    private final String id;
    private String username;
    private String passwordHash;
    private String email;
    private String fullName;
    private boolean active;
    private LocalDateTime createdAt;

    //Constructor
    public User(String username, String rawPassword, String email, String fullName){
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();

        this.username = username;
        this.passwordHash = PasswordHasher.hashPassword(rawPassword);
        this.email = email;
        this.fullName = fullName;
        this.active = true;
    }

    //Setter
    public void setEmail(String email){
        this.email = email;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setActive(boolean active){ // xem tài khoản còn hoạt động không
        this.active = active;
    }

    //Getter
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getFullName(){
        return fullName;
    }
    public boolean isActive(){
        return active;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // các phương thức quan trọng
    // kiểm tra mật khẩu
    public boolean checkPassword(String rawPassword){
        if(passwordHash.equals(PasswordHasher.hashPassword(rawPassword))){
            return true;
        }
        else{
            return false;
        }
    }
    //Đổi mật khẩu
    public boolean changePassword(String oldPassword, String newPassword){
        if(checkPassword(oldPassword)){
            this.passwordHash = PasswordHasher.hashPassword(newPassword);
            return true;
        }
        else{
            return false;
        }
    }

    //kiểm tra 2 tk có là 1 không
    @Override
    public boolean equals(Object o){
        if(o instanceof User) {
            return this.id.equals(((User) o).getId());
        }
        else {
            return false;
        }
    }
    @Override
    public int hashCode(){
        return id.hashCode(); //String có hashCode;
        }
}
