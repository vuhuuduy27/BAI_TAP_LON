package entity;

import java.time.LocalDateTime;

abstract class User {
    private String id;
    private String userName, fullName;
    private String password;
    private String email;
    private boolean active;
    private LocalDateTime createdAt;

    protected User(String userName, String password, String email, String fullName) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.active = false;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return this.id;}
    public String getUserName() { return this.userName;}
    public String getFullName() { return this.fullName;}
    public String getPassword() { return this.password;}
    public String getEmail() { return this.email;}

    public void setEmail(String email) {
        this.email = email;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean checkPassword(String raw) {
        if (raw.equals(this.password)) {
            return true;
        }
        return false;
    }

    public boolean changePassword(String old, String now) {
        if (old.equalls(this.password)) {
            this.password = now;
            return true;
        }
        return false;
    }

    public abstract Role gerRole();

    public String getInfo() {
        return "Username: " + this.userName + " | FullName: " + this.fullName + " | Email: " + this.email;
    }

}
