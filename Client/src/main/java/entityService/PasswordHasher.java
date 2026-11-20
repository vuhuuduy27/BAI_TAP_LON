
package entityService;

import entity.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class PasswordHasher {

    public static String hashPassword(String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("SHA-256");

            byte[] b = m.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < b.length; i++) {
                sb.append(
                        Integer.toString((b[i] & 0xff) + 0x100, 16)
                                .substring(1)
                );
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
