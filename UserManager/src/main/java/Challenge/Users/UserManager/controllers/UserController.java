package Challenge.Users.UserManager.controllers;

import Challenge.Users.UserManager.interfaces.iUserService;
import Challenge.Users.UserManager.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.HomeUrl)
public class UserController {

    public static final String HomeUrl = "api/Users";

    private final iUserService userService;

    public UserController(iUserService UserService){
        this.userService = UserService;
    }

    @GetMapping
    List<User> getUsers(){
       return userService.getAllUsers();
    }

    @GetMapping("/{Username}")
    public User getUserByUsername(@PathVariable String Username){
        return userService.findUserByUserName(Username);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{Username}")
    public void deleteUser(@PathVariable String Username){
        userService.deleteUser(Username);
        return;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.createNewUser(user);
    }

}
