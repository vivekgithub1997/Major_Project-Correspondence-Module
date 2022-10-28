package com.vivek.util;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	/*
	 * public static void sendemail(String to, String msg, String subject) throws
	 * MessagingException
	 * 
	 * { String host = "smtp.gmail.com"; Properties properties =
	 * System.getProperties(); properties.put("mail.smtp.host", host);
	 * properties.put("mail.smtp.port", "465");
	 * properties.put("mail.smtp.ssl.enable", "true");
	 * properties.put("mail.smtp.auth", "true"); Session session =
	 * Session.getInstance(properties, new javax.mail.Authenticator() {
	 * 
	 * protected PasswordAuthentication getPasswordAuthentication() {
	 * 
	 * return new PasswordAuthentication("chitrakoothospital108@gmail.com",
	 * "qjilolfmdfiwblgu");
	 * 
	 * }
	 * 
	 * }); session.setDebug(true);
	 * 
	 * MimeMessage mimeMessage = new MimeMessage(session);
	 * mimeMessage.setFrom("chitrakoothospital108@gmail.com");
	 * mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * mimeMessage.setSubject(subject); mimeMessage.setText(msg);
	 * 
	 * Transport.send(mimeMessage);
	 * 
	 * }
	 */

	public static void sendAttach(String message, String subject, String to, String from, String path) {

		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES " + properties);
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("chitrakoothospital108@gmail.com", "qjilolfmdfiwblgu");
			}

		});
		session.setDebug(true);
		MimeMessage m = new MimeMessage(session);

		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);

			MimeMultipart mimeMultipart = new MimeMultipart();
			MimeBodyPart textMime = new MimeBodyPart();

			MimeBodyPart fileMime = new MimeBodyPart();
			try {

				textMime.setText(message);

				File file = new File(path);
				fileMime.attachFile(file);

				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);

			} catch (Exception e) {

				e.printStackTrace();
			}

			m.setContent(mimeMultipart);

			Transport.send(m);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Sent success...................");

	}

}
