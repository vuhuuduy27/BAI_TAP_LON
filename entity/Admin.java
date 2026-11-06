package entity;

public class Admin extends User {
    public Admin(String userName, String password, String email) {
        super(userName, password, email);
    }

    /*public void activateUserStatus(String id) {
    }

    public void deactivateUserStatus(String id) {
    }
     */
    @Override
    public Roles getRole() {
        return Roles.ADMIN;
    }

    @Override
    public String getDescription() {
        return "Admin: " + super.getDescription();
    }
}




}
