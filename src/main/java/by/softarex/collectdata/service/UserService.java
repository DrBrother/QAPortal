package by.softarex.collectdata.service;

import by.softarex.collectdata.model.User;
import by.softarex.collectdata.repositories.UserRepository;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    public User save(User user) {
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(user.getPassword());
        emailService.sendSimpleMessage(newUser.getEmail(), "registration");
        return userRepository.save(newUser);
    }

    public User existsByEmail(User newUser) {
        User existingUser = userRepository.getByEmail(newUser.getEmail());
        if (existingUser == null) {
            return newUser;
        } else {
            return null;
        }
    }

    public User login(String passwordAndEmail) {
        JSONObject passwordAndEmailJson = new JSONObject(passwordAndEmail);
        String email = passwordAndEmailJson.getString("email");
        String password = passwordAndEmailJson.getString("password");
        User user = userRepository.getByEmail(email);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            } else return null;
        } else return null;
    }

    public void deleteUser(Long userId) {
        User deletedUser = userRepository.getById(userId);
        emailService.sendSimpleMessage(deletedUser.getEmail(), "deletion");
        userRepository.delete(deletedUser);
    }

    public User updateUser(String updatedUser, Long userId) {
        JSONObject updatedUserJson = new JSONObject(updatedUser);
        User existingUser = userRepository.getById(userId);
        existingUser.setFirstName(updatedUserJson.getString("firstName"));
        existingUser.setLastName(updatedUserJson.getString("lastName"));
        existingUser.setPhoneNumber(updatedUserJson.getString("phoneNumber"));
        existingUser.setEmail(updatedUserJson.getString("email"));
        return userRepository.save(existingUser);
    }

    public User updatePassword(String updatedUser, Long userId) {
        JSONObject updatedUserJson = new JSONObject(updatedUser);
        User existingUser = userRepository.getById(userId);
        if (updatedUserJson.getString("currentPassword").equals(existingUser.getPassword())) {
            if (!updatedUserJson.getString("newPassword").equals("")) {
                existingUser.setPassword(updatedUserJson.getString("newPassword"));
            }
            return userRepository.save(existingUser);
        } else return null;
    }

}
