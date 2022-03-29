package pl.sikor.williroi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "{username}")
    public UserModel getUserData(@PathVariable String username){
        return userService.getUser(username);
    }

    @PostMapping
    public void addUser(@RequestBody UserModel userModel) {
        userService.addNewUser(userModel);
    }

    @PatchMapping
    public UserModel patchUser(@RequestBody UserModel userModel){
        return userService.addHeliumAccount(userModel);
    }
}
