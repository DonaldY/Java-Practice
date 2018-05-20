package cn.donald.srp;

public class MailSender {

	private String fromAddress ;
	private String smtpHost;
	private String altSmtpHost;

	public MailSender(Configuration config){
		this.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		this.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		this.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}

	public void sendMail(Mail mail){

		try{

			sendEmail(mail,this.smtpHost);

		}catch(Exception e){

			try{

				sendEmail(mail,this.altSmtpHost);

			}catch (Exception ex){

				System.out.println("通过备用 SMTP服务器发送邮件失败: " + ex.getMessage());

			}

		}
	}

	private void sendEmail(Mail mail, String smtpHost) {

		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();

		buffer.append("To:").append(mail.getToAddress()).append("\n");

		buffer.append("Subject:").append(mail.getSubject()).append("\n");

		buffer.append("Content:").append(mail.getMessage()).append("\n");

		System.out.println(buffer.toString());
		
	}

	
}
