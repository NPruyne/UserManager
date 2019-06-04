package Challenge.Users.UserManager.model;

public class UserLink {
    private String rel;
    private String href;
    private String type;

    public UserLink(String href, String rel, String type) {
        this.type = type;
        this.rel = rel;
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
