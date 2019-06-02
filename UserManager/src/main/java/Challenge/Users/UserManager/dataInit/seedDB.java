package Challenge.Users.UserManager.dataInit;

import Challenge.Users.UserManager.interfaces.iUserContext;
import Challenge.Users.UserManager.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//Class that runs at startup to seed the in memory H2 database with data.
@Component
public class seedDB implements CommandLineRunner {

    private final iUserContext userContext;

    public seedDB(iUserContext UserContext){
        this.userContext = UserContext;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("ppicasso");
        user1.setName("Pablo Picasso");
        user1.setEmail("ppizzle@famouspainters.co.uk");
        userContext.save(user1);

        User user2 = new User();
        user2.setUsername("vgogh");
        user2.setName("Vincent van Gogh");
        user2.setEmail("vangoghfast@famouspainters.co.uk");
        userContext.save(user2);

        User user3 = new User();
        user3.setUsername("lvinci");
        user3.setName("Leonardo da Vinci");
        user3.setEmail("OldManLeo@famouspainters.co.uk");
        userContext.save(user3);

        User user4 = new User();
        user4.setUsername("awarhol");
        user4.setName("Andy Warhol");
        user4.setEmail("TomatoSoup@IEatPaint.com");
        userContext.save(user4);

        System.out.print("DB seeded successfully.");
    }
}
