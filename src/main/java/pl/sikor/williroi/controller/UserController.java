package pl.sikor.williroi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.service.UserService;

@CrossOrigin(origins = {"http://localhost:4200","http://10.0.2.2:8080", "https://williroi.herokuapp.com/"})
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<UserModel> getUserData(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body( userService.getUser(username) );
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body( userService.addNewUser(userModel) );
    }

    @PatchMapping
    public UserModel patchUser(@RequestBody UserModel userModel){
        return userService.addHeliumAccount(userModel);
    }
}
