package test.java;

import main.java.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//пример https://howtoprogram.xyz/2016/08/19/junit-5-nested-tests-examples/
//https://mkyong.com/junit5/junit-5-nested-test-examples/
class UserServiceTest {
    private UserService userService = null;

    @BeforeEach
    public void init() {
        userService = new UserService();
    }

    @Test
    public void logoutSuccess() {
        long userId = 1L;
        assertTrue(userService.logout(userId));

    }

    @Test
    public void resetPasswordExistingUser() {
        long userId = 1l;
        assertTrue(userService.resetPassword(userId));

    }

    @Test
    public void resetPasswordUserNotExist() {
        long userId = 5l;
        assertFalse(userService.resetPassword(userId));

    }

    @Nested
    @DisplayName("Test Login Feature")
    class LoginFeature {

        @Test
        void loginUsernamePasswordAreCorrect() {
            boolean actual = userService.login("admin", "password123");
            assertTrue(actual);
        }

        @Test
        void loginUsernamePasswordAreInCorrect() {
            boolean actual = userService.login("admin", "password123456");
            assertFalse(actual);
        }

        @Test
        void loginUsernamePasswordAreNulls() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                userService.login(null, null);
            });
            assertEquals("Username and password must not be null or empty", exception.getMessage());

        }

        @Test
        void loginUsernamePasswordAreEmpty() {

            assertThrows(IllegalArgumentException.class, () -> {
                userService.login("", "");
            });

        }
    }
    @Nested
    @DisplayName("Test ChangePassword Feature")
    class ChangePasswordFeature {
        @Test
        void changePasswordUserExistOldPasswordNewPasswordCorrect() {
            long userId = 1L; // existed user
            assertTrue(userService.changePassword(userId, "password123", "password123456"));
        }

        @Test
        void changePasswordUserNotExist() {
            long userId = 999L; // not existed user
            assertFalse(userService.changePassword(userId, "password123", "password123456"));
        }

        @Test
        void changePasswordUserExistOldPasswordAndNewPasswordEmpty() {
            long userId = 1L; // existed user
            assertFalse(userService.changePassword(userId, "", ""));
        }

        @Test
        void changePasswordUserExistOldPasswordEqualNewPassword() {
            long userId = 1L; // existed user
            assertFalse(userService.changePassword(userId, "password123", "password123"));
        }
    }

}