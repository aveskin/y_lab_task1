package ru.aveskin.util;

import ru.aveskin.user.controller.UserController;
import ru.aveskin.user.dto.UserLoginDto;
import ru.aveskin.user.dto.UserRegisterDto;
import ru.aveskin.user.model.User;

import java.util.Scanner;

public class ProgramInputHandler {
    private final UserController userController;
    private final static Scanner scanner = new Scanner(System.in);

    public ProgramInputHandler(UserController userController) {
        this.userController = userController;
    }

    public static int getChoice() {
        while (true) {
            try {
                System.out.println("Выберите действие: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    public void register() {
        String name = ProgramInputHandler.getString("Введите имя: ");
        String email = ProgramInputHandler.getString("Введите email: ");
        String password = ProgramInputHandler.getString("Введите пароль: ");

        boolean result = userController.register(new UserRegisterDto(name, email, password));
        if (result) {
            System.out.println("Регистрация успешна! Теперь можно войти в систему.");
        } else {
            System.out.println("Ошибка: пользователь с таким email уже существует.");
        }
    }

    public User login() {
        String email = ProgramInputHandler.getString("Введите email: ");
        String password = ProgramInputHandler.getString("Введите пароль: ");
        User loginedUser = userController.login(new UserLoginDto(email, password));
        if (loginedUser != null) {
            System.out.println("Пользователь успешно вошел в систему!");
            return loginedUser;
        } else {
            System.out.println("Ошибка: пользователь с таким email не существует или пароль не верный.");
            return null;
        }
    }
}
