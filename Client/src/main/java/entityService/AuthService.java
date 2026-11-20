
package entityService;

import entity.*;

public class AuthService {

    private UserManager userManager;

    public AuthService() {
        this.userManager = UserManager.getInstance();
    }

    public User login(String username, String password) {
        User u = userManager.getUserByUsername(username);

        return (u != null && u.getStatus() && u.checkPassword(password))
                ? u : null;
    }

    public boolean register(String username, String password,
                            String email, String fullName, Role role) {

        // Kiểm tra dữ liệu đầu vào
        if (username == null || password == null
                || email == null || fullName == null || role == null) {
            return false;
        }

        username = username.trim();
        email = email.trim();
        fullName = fullName.trim();

        if (username.isEmpty() || email.isEmpty()
                || fullName.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Kiểm tra định dạng username
        if (!Validator.validateUsername(username)) {
            return false;
        }

        // Kiểm tra định dạng email
        if (!Validator.validateEmail(email)) {
            return false;
        }

        // Kiểm tra độ mạnh password
        if (!Validator.validatePassword(password)) {
            return false;
        }

        // Kiểm tra username đã tồn tại
        if (userManager.isUsernameTaken(username)) {
            return false;
        }

        // Kiểm tra email đã tồn tại
        if (userManager.isEmailTaken(email)) {
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

        // Kích hoạt tài khoản mới
        newUser.setStatus(true);

        return userManager.addUser(newUser);
    }
}
