package ru.aveskin.user.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class UserRepositoryImplTest {
    private UserRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    void testSaveAndFindByEmail() {
        UserRegisterDto dto = new UserRegisterDto("Test", "Test@example.com", "password");
        userRepository.save(dto);
        User user = userRepository.findByEmail("Test@example.com");

        assertNotNull(user);
        assertEquals("Test", user.getName());
        assertEquals("Test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    void testLogin_Success() {
        UserRegisterDto dto = new UserRegisterDto("Test", "Test@example.com", "password");
        userRepository.save(dto);

        UserLoginDto loginDto = new UserLoginDto("Test@example.com", "password");
        User user = userRepository.login(loginDto);

        assertNotNull(user);
        assertEquals("Test@example.com", user.getEmail());
    }

    @Test
    void testLogin_Failure() {
        UserRegisterDto dto = new UserRegisterDto("Test", "Test@example.com", "password");
        userRepository.save(dto);

        UserLoginDto loginDto = new UserLoginDto("Test@example.com", "wrongpassword");
        User user = userRepository.login(loginDto);

        assertNull(user);
    }

    @Test
    void testDeleteUser() {
        UserRegisterDto dto = new UserRegisterDto("Test", "Test@example.com", "password");
        userRepository.save(dto);
        User user = userRepository.findByEmail("Test@example.com");

        assertNotNull(user);

        userRepository.delete(user);
        assertNull(userRepository.findByEmail("Test@example.com"));
    }

    @Test
    void testFindAllUsers() {
        userRepository.save(new UserRegisterDto("Test", "Test@example.com", "password"));
        userRepository.save(new UserRegisterDto("Test2", "Test2@example.com", "password123"));

        List<User> users = userRepository.findAllUsers();

        assertEquals(2, users.size());
    }
}