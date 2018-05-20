package cn.donald.srp;


import java.io.IOException;
import java.util.List;

public class PromotionJob {

	private ProductService productService ; //获取production service

	private UserService userService ; // 获取UserService

	
	public void run() throws IOException {

		List<Product> list = this.productService.getPromotionProduct();

		/**
		 * 每个产品发送给关注其的用户
		 */

		// TODO 一个用户订阅了多个产品降价，发送几个邮件

		for (Product product : list) {

			System.out.println(product);

			List<User> users = this.userService.getUsers(product);

			Configuration cfg = Configuration.getInstance();

			MailSender mailSender = new MailSender(cfg);

			for (User user : users) {

				mailSender.sendMail(new Mail(user));

			}


		}
		
	}


}
