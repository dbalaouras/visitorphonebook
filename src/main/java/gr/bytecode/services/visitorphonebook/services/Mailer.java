package gr.bytecode.services.visitorphonebook.services;

import java.util.concurrent.Future;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * Mail service
 * 
 * @author Dimitrios Balaouras
 * @version %G%
 * @since %I%
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
	 * The http session
	 */
	@Autowired
	HttpSession session;

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

		// get the current user
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = user.getUsername(); // get logged in username

		String messageText = body.replace("${username}", name);

		// set the message text
		message.setText(messageText);

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