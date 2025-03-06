package ru.aveskin.user.service.impl;

import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;
import ru.aveskin.user.repository.UserRepository;
import ru.aveskin.user.repository.impl.UserRepositoryImpl;
import ru.aveskin.user.service.UserService;
import ru.aveskin.util.ProgramInputHandler;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (userRepository.getByEmail(userRegisterDto.email()) != null) {
            return false;
        }
        userRepository.save(userRegisterDto);
        return true;
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        return userRepository.login(userLoginDto);
    }

    @Override
    public void changeUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
        System.out.println("Пользователь удален");
    }

    @Override
    public void showUserList() {
        List<User> userList = userRepository.findAllUsers();
        userList.forEach(user -> System.out.println(user.getEmail()));
    }

    @Override
    public User getUserByEmail() {
        System.out.println("\nПолучение аккаунта по email пользователя:");
        String email = ProgramInputHandler.getString("Введите email пользователя: ");
        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("Пользователь не найден");
            return null;
        }

        return user;
    }
}
