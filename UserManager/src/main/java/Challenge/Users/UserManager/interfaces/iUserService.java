package Challenge.Users.UserManager.interfaces;

import Challenge.Users.UserManager.model.User;

import java.util.List;

public interface iUserService {
    User findUserByUserName(String username);
    User createNewUser(User newUser);
    List<User> getAllUsers();
    void deleteUser(String Username);
    User updateUser(User updatedUser);


}
