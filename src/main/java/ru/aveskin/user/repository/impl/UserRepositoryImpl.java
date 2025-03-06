package ru.aveskin.user.repository.impl;

import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;
import ru.aveskin.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(UserRegisterDto userRegisterDto) {
        users.put(userRegisterDto.email(),
                new User(userRegisterDto.name(),
                        userRegisterDto.email(),
                        userRegisterDto.password(),
                        false));
    }

    @Override
    public User getByEmail(String email) {
        return users.get(email);
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        User user = users.get(userLoginDto.email());
        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(userLoginDto.password())) {
            return user;
        }
        return null;
    }

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        users.forEach((s, user) -> userList.add(user));
        return userList;
    }

    @Override
    public User findByEmail(String email) {
        return users.get(email);
    }
}
