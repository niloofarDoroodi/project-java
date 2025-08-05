package org.example.service.implementation;

import org.example.entity.User;
import org.example.repository.UserRepo;
import org.example.service.UserService;
import org.example.util.Validation;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String signUp(User user) {
        if (userRepo.isExist(user.getUsername()) == null) {
            if (Validation.validPassword(user.getPassword()) && Validation.validEmail(user.getEmail())) {
                userRepo.signUp(user);
                return "Successfully signed up.";
            } else {
                if (!Validation.validPassword(user.getPassword())) {
                    return "Your password is not valid.";
                } else {
                    return "Your email is not valid.";
                }
            }
        } else {
            return "User already exits.";
        }
    }

    @Override
    public User signIn(String username, String password) {
        if (userRepo.isExist(username) == null) {
            return null;
        }
        return userRepo.signIn(username, password);
    }

}
