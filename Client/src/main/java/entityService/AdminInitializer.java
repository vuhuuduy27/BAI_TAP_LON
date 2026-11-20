package entityService;

import entity.Admin;
import entity.Role;

public class AdminInitializer {

    //default Admin account
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "Admin@123";
    private static final String DEFAULT_EMAIL = "admin@auction.com";

    private final UserManager userManager;

    public AdminInitializer() {
        this.userManager = UserManager.getInstance();
    }

    // Automatically create the default Admin if it does not exist
    public void initializeDefaultAdmin() {

        if (userManager.isUsernameTaken(DEFAULT_USERNAME)) {
            System.out.println("Default Admin already exists.");
            return;
        }

        String hashedPassword =
                PasswordHasher.hashPassword(DEFAULT_PASSWORD);

        Admin admin = new Admin(
                DEFAULT_USERNAME,
                hashedPassword,
                DEFAULT_EMAIL
        );

        admin.setStatus(true);

        if (userManager.addUser(admin)) {
            System.out.println("Default Admin created successfully.");
            System.out.println("Username: " + DEFAULT_USERNAME);
            System.out.println("Password: " + DEFAULT_PASSWORD);
        } else {
            System.out.println("Failed to create default Admin.");
        }
    }

    // Create an additional Admin account
    public boolean createAdmin(String username, String password, String email) {

        if (username == null || username.trim().isEmpty()
                || password == null || password.isEmpty()
                || email == null || email.trim().isEmpty()) {
            return false;
        }

        username = username.trim();
        email = email.trim();

        if (userManager.isUsernameTaken(username)
                || userManager.isEmailTaken(email)) {
            return false;
        }

        String hashedPassword = PasswordHasher.hashPassword(password);

        Admin admin = new Admin(
                username,
                hashedPassword,
                email
        );

        admin.setStatus(true);
        return userManager.addUser(admin);
    }
}

