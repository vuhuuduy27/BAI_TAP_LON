package entityService;

public class Validator {

    // Validate username: 4-20 characters, letters, numbers and underscore
    public static boolean validateUsername(String username) {

        if (username == null) {
            return false;
        }

        return username.matches("^[a-zA-Z0-9_]{4,20}$");
    }

    // Validate email format
    public static boolean validateEmail(String email) {

        if (email == null) {
            return false;
        }

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }

    // Validate password: at least 8 characters,
    // one uppercase, one lowercase and one digit
    public static boolean validatePassword(String password) {

        if (password == null) {
            return false;
        }

        return password.matches(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$"
        );
    }
}

