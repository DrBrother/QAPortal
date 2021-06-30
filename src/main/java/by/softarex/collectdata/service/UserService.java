package by.softarex.collectdata.service;

import by.softarex.collectdata.dto.PasswordDTO;
import by.softarex.collectdata.model.Option;
import by.softarex.collectdata.model.User;
import by.softarex.collectdata.repositories.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public User existsByEmail(User newUser) {
        return userRepository.getByEmail(newUser.getEmail());
    }

    public User login(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User userCheck = userRepository.getByEmail(email);
        if (userCheck != null) {
            if (password.equals(user.getPassword())) {
                return userCheck;
            } else return null;
        }
        return null;
    }

    public void deleteUser(Long userId) {
        User deletedUser = userRepository.getById(userId);
        emailService.sendSimpleMessage(deletedUser.getEmail(), "deletion");
        userRepository.delete(deletedUser);
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

    public User updateUser(User user, Long userId) {
        User existingUser = userRepository.getById(userId);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(existingUser);
    }

//    public User updatePassword(String updatedUser, Long userId) {
//        JSONObject updatedUserJson = new JSONObject(updatedUser);
//        User existingUser = userRepository.getById(userId);
//        if (updatedUserJson.getString("currentPassword").equals(existingUser.getPassword())) {
//            if (!updatedUserJson.getString("newPassword").equals("")) {
//                existingUser.setPassword(updatedUserJson.getString("newPassword"));
//            }
//            return userRepository.save(existingUser);
//        }
//        return null;
//    }


    public User updatePassword(PasswordDTO passwordDTO, Long userId){
        Optional<User> userOptional= userRepository.findById(userId);
        User user = userOptional.get();
        if(passwordDTO.getPassword().equals(user.getPassword())){
            user.setPassword(passwordDTO.getNewPassword());
            return userRepository.save(user);
        } else return null;
    }

    public User getUserById(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }
}


