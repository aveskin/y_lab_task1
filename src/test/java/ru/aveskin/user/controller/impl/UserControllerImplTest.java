package ru.aveskin.user.controller.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;
import ru.aveskin.user.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerImplTest {
    private UserControllerImpl userController;
    private UserService userServiceMock;

    @BeforeEach
    void setUp() {
        userServiceMock = Mockito.mock(UserService.class);
        userController = new UserControllerImpl(userServiceMock);
    }

    @Test
    void testRegister_Success() {
        UserRegisterDto dto = new UserRegisterDto("test", "test@example.com", "password");
        when(userServiceMock.register(dto)).thenReturn(true);

        boolean result = userController.register(dto);

        assertTrue(result);
        verify(userServiceMock, times(1)).register(dto);
    }

    @Test
    void testRegister_Failure() {
        UserRegisterDto dto = new UserRegisterDto("test", "test@example.com", "password");
        when(userServiceMock.register(dto)).thenReturn(false);

        boolean result = userController.register(dto);

        assertFalse(result);
        verify(userServiceMock, times(1)).register(dto);
    }

    @Test
    void testLogin_Success() {
        UserLoginDto dto = new UserLoginDto("test@example.com", "password");
        User user = new User("test", "test@example.com", "password", false);
        when(userServiceMock.login(dto)).thenReturn(user);

        User result = userController.login(dto);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(userServiceMock, times(1)).login(dto);
    }

    @Test
    void testLogin_Failure() {
        UserLoginDto dto = new UserLoginDto("test@example.com", "wrongpassword");
        when(userServiceMock.login(dto)).thenReturn(null);

        User result = userController.login(dto);

        assertNull(result);
        verify(userServiceMock, times(1)).login(dto);
    }

    @Test
    void testChangeUser() {
        User user = new User("test", "test@example.com", "password", false);

        userController.changeUser(user);

        verify(userServiceMock, times(1)).changeUser(user);
    }

    @Test
    void testDeleteUser() {
        User user = new User("test", "test@example.com", "password", false);

        userController.deleteUser(user);

        verify(userServiceMock, times(1)).deleteUser(user);
    }

    @Test
    void testShowUserList() {
        userController.showUserList();

        verify(userServiceMock, times(1)).showUserList();
    }
}