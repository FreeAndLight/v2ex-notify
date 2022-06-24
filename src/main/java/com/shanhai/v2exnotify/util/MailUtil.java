package com.shanhai.v2exnotify.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件工具类
 */
public class MailUtil {

	/**
	 * 通过线程池异步发送邮件
	 *
	 * @param toEmail  发送邮件给谁
	 * @param title    标题
	 * @param emailMsg 发送邮件的内容
	 * @throws Exception
	 */
	public static void sendMailByThreadPool(String toEmail, String title, String emailMsg) {
		ThreadPoolUtils.execute(() -> {
			try {
				MailUtil.sendMail(toEmail, title, emailMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 发送邮件
	 *
	 * @param toEmail  发送邮件给谁
	 * @param title    标题
	 * @param emailMsg 发送邮件的内容
	 * @throws Exception
	 */
	public static void sendMail(String toEmail, String title, String emailMsg) throws Exception {
		//1_创建Java程序与163邮件服务器的连接对象
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");//设置发送方邮箱服务器
		props.put("mail.smtp.auth", "true");//设置是否需要身份证
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//发送方服务器账号设置
				//需要在163官方邮箱服务器，开启设置——>POP3/SMTP/IMAP服务能让其在本地客户端上收发邮件
				//QQ邮箱——>设置——>账号管理——>开启
				//开启后，需要验证密保，发送相关内容后会弹出密码
				return new PasswordAuthentication("123456@163.com", "dsadasd");
			}
		};
		Session session = Session.getInstance(props, auth);
		//2_创建一封邮件
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("123456@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
		message.setSubject(title);
		message.setContent(emailMsg, "text/html;charset=UTF-8");
		//3_发送邮件
		Transport.send(message);
		System.out.println("发送成功");
	}

}