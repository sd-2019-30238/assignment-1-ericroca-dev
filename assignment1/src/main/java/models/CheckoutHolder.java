package models;

import java.util.List;

public class CheckoutHolder {

    private String username;
    private List<String> names;

    public CheckoutHolder() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
