package cn.donald.srp;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<User> query(String sql){
		
		List users = new ArrayList();

		for (int i = 1; i <= 3; i++) {

			User user = new User();

			user.setName("User" + i);

			user.setEmail("aa@bb.com");

			users.add(user);

		}

		return users;
	}
}
