package ru.aveskin.user.admin.controller.impl;

import ru.aveskin.user.admin.controller.AdminController;
import ru.aveskin.user.model.User;
import ru.aveskin.user.service.UserService;
import ru.aveskin.user.service.impl.UserServiceImpl;

public class AdminControllerImpl implements AdminController {
    private final UserService userService = new UserServiceImpl();

    @Override
    public void delete() {
        User user = userService.getUserByEmail();
        if (user != null) {
            userService.deleteUser(user);
        }
    }
}
