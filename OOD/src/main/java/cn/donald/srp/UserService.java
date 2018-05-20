package cn.donald.srp;

import java.util.List;

/**
 * Created by DonaldY on 2017/6/22.
 */
public class UserService {

    private User user;


    public List<User> getUsers(Product product) {

        String sql = "Select * from user where " + product.getId() + "in (select pid from product " +
            "where uid = id)";

        return DBUtil.query(sql);

    }
}
