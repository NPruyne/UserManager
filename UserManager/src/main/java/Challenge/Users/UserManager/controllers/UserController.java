package Challenge.Users.UserManager.controllers;

import Challenge.Users.UserManager.interfaces.iUserService;
import Challenge.Users.UserManager.model.User;
import Challenge.Users.UserManager.model.UserContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(UserController.UserBaseUri)
public class UserController {

    public static final String UserBaseUri = "api/Users";

    private final iUserService userService;
    private String currentHost;

    public UserController(iUserService UserService){
        this.userService = UserService;
        this.currentHost = "http://localhost:8080";
    }

    private String getCompleteBaseUri(){
        return currentHost + "/" + UserBaseUri; 
    }
    
    @GetMapping
    List<UserContainer> getUsers(){
       List<UserContainer> containers = new ArrayList<UserContainer>();
       containers.clear();
       List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            containers.add(new UserContainer(user, getCompleteBaseUri()));
        }
        return containers;
    }

    @GetMapping("/{Username}")
    public ResponseEntity<UserContainer> getUserByUsername(@PathVariable String Username){
        User foundUser = userService.findUserByUserName(Username);
        if(foundUser == null)
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(new UserContainer(foundUser, getCompleteBaseUri()));
    }

    //Responds to requests to user base + username PUT requests, re
    @PutMapping("/{username}")
    public UserContainer updateUser(@RequestBody User user, @PathVariable String Username){
        user.setUsername(Username);
        return new UserContainer(userService.updateUser(user), getCompleteBaseUri());
    }

    //Responds to requests to user base + username DELETE requests, responds with empty body and 200 to signify delete was successful
    @DeleteMapping("/{Username}")
    public void deleteUser(@PathVariable String Username){
        userService.deleteUser(Username);
        return;
    }

    // Respond to User Base Post requests, respond with the user as body, and 201 as response status
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserContainer createUser(@RequestBody User user){

        return new UserContainer(userService.createNewUser(user), getCompleteBaseUri());
    }

}
