package ru.aveskin.user.repository;

import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;

import java.util.List;

public interface UserRepository {
    void save(UserRegisterDto userRegisterDto);

    User getByEmail(String email);

    User login(UserLoginDto userLoginDto);

    void save(User user);

    void delete(User user);

    List<User> findAllUsers();

    User findByEmail(String email);
}
