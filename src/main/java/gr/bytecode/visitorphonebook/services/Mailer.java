package gr.bytecode.visitorphonebook.services;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

/**
 * Mail service
 * 
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Service("mailer")
public class Mailer {

	/**
	 * A Spring's MailSender instance configured in app-context.xml
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * The email address of the site admin
	 */
	@Value("${admin-email}")
	private String adminEmail;

	/**
	 * Compose and send an email message
	 * */
	@Async
	public Future<Boolean> sendMailAsync(String from, String to,
			String subject, String body) {

		// send the mail
		boolean result = sendMail(from, to, subject, body);

		return new AsyncResult<Boolean>(result);
	}

	/**
	 * Compose and send an email message
	 * */
	public boolean sendMail(String from, String to, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setBcc(to);
		message.setSubject(subject);

		// set the message text
		message.setText(body);

		mailSender.send(message);

		// TODO: return false when the above fails
		return true;
	}

	/**
	 * Compose and send an email message
	 * */
	@Async
	public Future<Boolean> sendMailAsync(String to, String subject, String body) {
		return sendMailAsync(adminEmail, to, subject, body);
	}

}