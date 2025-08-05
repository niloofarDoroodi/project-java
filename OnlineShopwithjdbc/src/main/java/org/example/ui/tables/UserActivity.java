package org.example.ui.tables;

import org.example.entity.User;
import org.example.service.UserService;

import java.util.Scanner;

import org.example.ui.Printer;
import org.example.util.ApplicationContext;

public class UserActivity {
    static Scanner input = new Scanner(System.in);
    private static final UserService userService = ApplicationContext.getUserService();

    public static void signup() {
        User user = new User();

        System.out.println("Enter Your Name: ");
        user.setName(input.nextLine());

        System.out.println("Enter Your Username: ");
        user.setUsername(input.nextLine());

        System.out.println("Enter Your Password: ");
        user.setPassword(input.nextLine());

        System.out.println("Enter Your Email: ");
        user.setEmail(input.nextLine());

        Printer.printMsg(userService.signUp(user));
    }

    public static User login() {
        System.out.println("Enter Your Username: ");
        String username = input.nextLine();

        System.out.println("Enter Your Password: ");
        String password = input.nextLine();

        return userService.signIn(username, password);
    }

}
