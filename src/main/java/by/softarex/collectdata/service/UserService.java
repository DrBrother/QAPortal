package by.softarex.collectdata.service;

import by.softarex.collectdata.model.User;
import by.softarex.collectdata.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long userId){
        return userRepository.getById(userId);
    }
}
