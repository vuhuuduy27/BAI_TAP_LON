
package entityService;

import entity.*;

public class AuthService {
    private UserManager userManager;

    public AuthService() {
        this.userManager = UserManager.getInstance();
    }

    public boolean login(String username, String password) {
        for (User u : userManager.getAllUser().values()) {
            if (u.getUserName().equalsIgnoreCase(username)) {
                return u.checkPassword(password);
            }
        }
        return false;
    }

    public boolean register(String username, String password,
                            String email, String fullName, Role role) {

        // Kiểm tra username đã tồn tại
        if (userManager.isUsernameTaken(username)) {
            return false;
        }

        // Không cho phép tự đăng ký Admin
        if (role != Role.BIDDER && role != Role.SELLER) {
            return false;
        }

        // Hash password chỉ một lần
        String hashedPassword = PasswordHasher.hashPassword(password);

        User newUser;

        if (role == Role.BIDDER) {
            newUser = new Bidder(
                    username, hashedPassword, email, 0L, fullName
            );
        } else {
            newUser = new Seller(
                    username, hashedPassword, email, fullName
            );
        }

        return userManager.addUser(newUser);
    }
}
