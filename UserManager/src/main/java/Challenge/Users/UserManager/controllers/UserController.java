package Challenge.Users.UserManager.controllers;

import Challenge.Users.UserManager.interfaces.iUserService;
import Challenge.Users.UserManager.model.User;
import Challenge.Users.UserManager.model.UserContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(UserController.UserBaseUri)
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    public static final String UserBaseUri = "api/Users";

    private final iUserService userService;
    private String currentHost;


    public UserController(iUserService UserService){
        this.userService = UserService;
    }

    private String getCompleteBaseUri(){
        return currentHost + "/" + UserBaseUri;
    }

    //Piece together the base host address from the current request
    private void getCurrentHost(){
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        currentHost = builder.build().getScheme() + "://" + builder.build().getHost();
        if(builder.build().getPort() != 80)
            currentHost += ":" + builder.build().getPort();
    }

    //Responds to Get requests at base address, Return all the users in the Database
    @GetMapping
    List<UserContainer> getUsers(HttpServletRequest request){

       getCurrentHost();
       logger.info("GET Request called for all users.");
       List<UserContainer> containers = new ArrayList<UserContainer>();
       containers.clear();
       List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            containers.add(new UserContainer(user, getCompleteBaseUri()));
        }

        return containers;
    }

    //Responds to Get requests with a given username, Find and return user. Return 204 for invalid user.
    @GetMapping("/{Username}")
    public ResponseEntity<UserContainer> getUserByUsername(@PathVariable String Username){
        getCurrentHost();
        logger.info("GET request called for user " + Username);
        User foundUser = userService.findUserByUserName(Username);
        if(foundUser == null)
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(new UserContainer(foundUser, getCompleteBaseUri()));
    }

    //Responds to requests to user base + username PUT requests, responds with 200 and the user with links.
    @PutMapping("/{Username}")
    public UserContainer updateUser(@RequestBody User user, @PathVariable String Username){
        getCurrentHost();
        logger.info("PUT Request called to modify user " + Username);
        user.setUsername(Username);
        return new UserContainer(userService.updateUser(user), getCompleteBaseUri());
    }

    //Responds to requests to user base + username DELETE requests, responds with empty body and 200 to signify delete was successful
    @DeleteMapping("/{Username}")
    public void deleteUser(@PathVariable String Username){
        getCurrentHost();
        logger.info("DELETE Request called to delete the user " + Username);
        userService.deleteUser(Username);
        return;
    }

    // Respond to User Base Post requests, respond with the user with links as body, and 201 as response status
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserContainer createUser(@RequestBody User user){
        getCurrentHost();
        logger.info("POST Request called to create the user " + user.getUsername());
        return new UserContainer(userService.createNewUser(user), getCompleteBaseUri());
    }

}
