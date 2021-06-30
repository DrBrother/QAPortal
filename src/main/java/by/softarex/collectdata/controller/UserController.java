package by.softarex.collectdata.controller;

import by.softarex.collectdata.dto.PasswordDTO;
import by.softarex.collectdata.model.User;
import by.softarex.collectdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User newUser, HttpServletRequest request) {
        User user = userService.existsByEmail(newUser);
        if (user == null) {
            request.getSession().setAttribute("authenticated", true);
            return ResponseEntity.ok(userService.save(newUser));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) {
        User existingUser = userService.login(user);
        if (existingUser != null) {
            request.getSession().setAttribute("authenticated", true);
            return ResponseEntity.ok(existingUser);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/logout")
    public ResponseEntity<User> logout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {
        User newUser = userService.updateUser(user, userId);
        if (newUser != null) {
            return ResponseEntity.ok(newUser);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("users/{userId}/password")
    public ResponseEntity<User> updatePassword(@RequestBody PasswordDTO passwordDTO, @PathVariable Long userId) {
        User newUser = userService.updatePassword(passwordDTO, userId);
        if (newUser != null) {
            return ResponseEntity.ok(newUser);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
