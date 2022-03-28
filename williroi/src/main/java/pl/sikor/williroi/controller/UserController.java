package pl.sikor.williroi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public void getUserData(){
        userService
    }

    @PostMapping(value = "{heliuum_account_address}")
    public void addUser(@PathVariable String heliuum_account_address) {
        userService.addNewUser(heliuum_account_address);
    }
}
