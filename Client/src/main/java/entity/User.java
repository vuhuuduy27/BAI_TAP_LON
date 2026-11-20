
package entity;

import entityService.PasswordHasher;
import java.time.LocalDateTime;
import java.util.UUID;

abstract public class User {
    private String id;
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private boolean status;
    private LocalDateTime createdAt;

    // Constructor cũ để không làm hỏng code đang sử dụng
    protected User(String userName, String password, String email) {
        this(userName, password, email, userName);
    }

    // Constructor mới có fullName
    protected User(String userName, String password,
                   String email, String fullName) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.status = false;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return this.id;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public boolean getStatus() {
        return this.status;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean checkPassword(String raw) {
        if (raw == null || this.password == null) {
            return false;
        }

        return PasswordHasher.hashPassword(raw).equals(this.password);
    }

    public boolean changePassword(String old, String now) {
        if (old == null || now == null) {
            return false;
        }

        if (checkPassword(old)) {
            this.password = PasswordHasher.hashPassword(now);
            return true;
        }

        return false;
    }

    public abstract Role getRole();

    public String getDescription() {
        return this.userName + " | Full name: " + this.fullName
                + " | Email: " + this.email;
    }
}
