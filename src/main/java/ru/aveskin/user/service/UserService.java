package ru.aveskin.user.service;

import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;

public interface UserService {
    boolean register(UserRegisterDto userRegisterDto);

    User login(UserLoginDto userLoginDto);

    void changeUser(User user);

    void deleteUser(User user);


    void showUserList();

    User getUserByEmail();
}

