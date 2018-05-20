package cn.donald.srp;

import java.util.List;

/**
 * Created by DonaldY on 2017/6/18.
 */
public class User {

    private String name;
    private String emailAddress;

    private List<Product> subscribedProducts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmail(String email) {
        this.emailAddress = email;
    }

    public List<Product> getSubscribedProducts() {
        return this.subscribedProducts;
    }
}
