package Challenge.Users.UserManager.service;

import Challenge.Users.UserManager.interfaces.iUserContext;
import Challenge.Users.UserManager.interfaces.iUserService;
import Challenge.Users.UserManager.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements iUserService {

    private final iUserContext userContext;

    public UserService(iUserContext UserContext) {
        this.userContext = UserContext;
    }

    @Override
    public User findUserByUserName(String username) {
        if(userContext.findById(username).isPresent()) {
            return userContext.findById(username).get();
        }
        else{
            return null;
        }

    }

    @Override
    public User createNewUser(User newUser) {
        return userContext.save(newUser);

    }

    @Override
    public List<User> getAllUsers() {
        return userContext.findAll();
    }

    @Override
    public void deleteUser(String Username) {
        if(userContext.existsById(Username)) {
            userContext.deleteById(Username);
        }
        return;
    }

    @Override
    public User updateUser(User updatedUser) {
        return userContext.save(updatedUser);
    }
}
