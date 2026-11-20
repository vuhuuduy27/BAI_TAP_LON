package entityService;

import entity.User;

public final class UserSession {

    private static User currentUser;

    // Lưu người dùng sau khi đăng nhập thành công
    public static void start(User user) {
        currentUser = user;
    }

    // Lấy thông tin người dùng hiện tại
    public static User getCurrentUser() {
        return currentUser;
    }

    // Kiểm tra có người dùng đang đăng nhập hay không
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // Kết thúc phiên đăng nhập
    public static void end() {
        currentUser = null;
    }
}

