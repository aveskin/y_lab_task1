package ru.aveskin.user.controller.impl;

import ru.aveskin.user.controller.UserController;
import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;
import ru.aveskin.user.service.UserService;
import ru.aveskin.user.service.impl.UserServiceImpl;

public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @Override
    public void changeUser(User user) {
        userService.changeUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userService.deleteUser(user);
    }

    @Override
    public void showUserList() {
        userService.showUserList();
    }

}
