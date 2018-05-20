package cn.donald.srp;

import java.util.List;

/**
 * Created by DonaldY on 2017/6/16.
 */
public class Mail {

    private User user ;

    public Mail(User user) {
        this.user = user;
    }

    public String getToAddress() {
        return this.user.getEmailAddress();
    }

    public String getSubject() {
        return "您关注的产品降价了";
    }

    public String getMessage() {
        return "尊敬的 "+user.getName()+", 您关注的产品 " + this.buildProductDescList() + " 降价了，欢迎购买!" ;
    }

    private String buildProductDescList() {
        List<Product> products = this.user.getSubscribedProducts();

        return null;
    }

}
