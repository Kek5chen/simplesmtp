package me.itsjbey.simplesmtp.smtp;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SimpleMailSession {

	private Session session;

	String from, to, password, subject, message;

	public SimpleMailSession(String host, final String from, final String password, final String to) {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, password);

			}

		});

		this.from = from;
		this.to = to;
		this.password = password;

	}

	public SimpleMailSession(String host, final String from, final String password, final String to, boolean debug) {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, password);

			}

		});

		// Used to debug SMTP issues
		session.setDebug(debug);

		this.from = from;
		this.to = to;
		this.password = password;

	}

	public SimpleMailSession(String host, final String from, final String to) {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		session = Session.getInstance(properties);

		this.from = from;
		this.to = to;

	}

	public SimpleMailSession(String host, final String from, final String to, boolean debug) {

		Properties properties = new Properties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		session = Session.getInstance(properties);

		// Used to debug SMTP issues
		session.setDebug(debug);

		this.from = from;
		this.to = to;

	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void send(String message, String subject) throws MessagingException {

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from));

		email.setRecipients(Message.RecipientType.TO, to);

		email.setSubject(subject);

		email.setText(message);

		Transport.send(email);

	}

	public void send() throws MessagingException {

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from));

		email.setRecipients(Message.RecipientType.TO, to);

		email.setSubject(subject);

		email.setText(message);

		Transport.send(email);

	}

}
