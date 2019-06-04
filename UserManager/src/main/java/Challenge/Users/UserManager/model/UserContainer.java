package Challenge.Users.UserManager.model;

import java.util.ArrayList;
import java.util.List;

public class UserContainer {
    private User user;
    private List<UserLink> links;

    //Take in a User, generate appropriate links
    public UserContainer(User user, String BaseUri) {
        this.user = user;

        this.links = new ArrayList<UserLink>();

        //Would add Auth logic here to limit options
        UserLink viewLink = new UserLink(BaseUri + "/" + user.getUsername(),"View", "GET");
        links.add(viewLink);
        UserLink updateLink = new UserLink(BaseUri + "/" + user.getUsername(), "Update", "PUT");
        links.add(updateLink);
        UserLink deleteLink = new UserLink(BaseUri + "/" + user.getUsername(), "Delete", "DELETE");
        links.add(deleteLink);
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserLink> getLinks() {
        return links;
    }

    public void setLinks(List<UserLink> links) {
        this.links = links;
    }


}
