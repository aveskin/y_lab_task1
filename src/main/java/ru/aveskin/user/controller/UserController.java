package ru.aveskin.user.controller;

import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;

public interface UserController {
    boolean register(UserRegisterDto userRegisterDto);

    User login(UserLoginDto userLoginDto);

    void changeUser(User user);

    void deleteUser(User user);

    void showUserList();

}
