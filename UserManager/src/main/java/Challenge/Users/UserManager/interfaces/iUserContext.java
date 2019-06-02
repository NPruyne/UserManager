package Challenge.Users.UserManager.interfaces;

import Challenge.Users.UserManager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// Just wrap JpaRepository and use it's functionality.
public interface iUserContext extends JpaRepository<User, String> {

}
