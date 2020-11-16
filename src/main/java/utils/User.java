package utils;

import lombok.AllArgsConstructor;

/**
 * Класс для тестовых пользователей
 */
//аннотация для генерации конструктора со всеми параметрами
@AllArgsConstructor
public enum User {

    TEST_USER("test_auto", "159852Ff");

    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
