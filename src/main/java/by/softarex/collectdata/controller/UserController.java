package by.softarex.collectdata.controller;

import by.softarex.collectdata.model.User;
import by.softarex.collectdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User newUser) {
        User user = userService.existsByEmail(newUser);
        if (user == null) {
            return ResponseEntity.ok(userService.save(newUser));
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) {
        if (userService.login(user) != null) {
        //    request.getSession().setAttribute("authenticated", true);
            return ResponseEntity.ok(user);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/logout")
    public ResponseEntity<User> logout(HttpServletRequest request) {
       // request.getSession().setAttribute("authenticated", null);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long userId) {
        User newUser = userService.updateUser(user, userId);
        if (newUser != null) {
            return ResponseEntity.ok(newUser);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("users/{userId}/password")
    public ResponseEntity<User> updatePassword(@RequestBody User user, @PathVariable Long userId) {
        User newUser = userService.updatePassword(user, userId);
        if (newUser != null) {
            return ResponseEntity.ok(newUser);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
