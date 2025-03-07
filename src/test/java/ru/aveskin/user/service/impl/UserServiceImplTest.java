package ru.aveskin.user.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;
import ru.aveskin.user.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void register_ShouldReturnTrue_WhenUserNotExists() {
        UserRegisterDto dto = new UserRegisterDto("Test_user", "Test_user@example.com", "password");

        when(userRepository.getByEmail(dto.email())).thenReturn(null);

        boolean result = userService.register(dto);

        assertTrue(result);
        verify(userRepository, times(1)).save(dto);
    }

    @Test
    void register_ShouldReturnFalse_WhenUserAlreadyExists() {
        UserRegisterDto dto = new UserRegisterDto("Test_user", "Test_user@example.com", "password");
        when(userRepository.getByEmail(dto.email())).thenReturn(
                new User("Test_user",
                        "Test_user@example.com",
                        "password", false));

        boolean result = userService.register(dto);

        assertFalse(result);
        verify(userRepository, never()).save(dto);
    }

    @Test
    void login_ShouldReturnUser_WhenCredentialsAreCorrect() {
        UserLoginDto dto = new UserLoginDto("Test_user@example.com", "password");
        User user = new User("Test_user", "Test_user@example.com", "password", false);

        when(userRepository.login(dto)).thenReturn(user);

        User result = userService.login(dto);

        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void login_ShouldReturnNull_WhenCredentialsAreIncorrect() {
        UserLoginDto dto = new UserLoginDto("Test_user@example.com", "wrongpassword");

        when(userRepository.login(dto)).thenReturn(null);

        User result = userService.login(dto);

        assertNull(result);
    }
}