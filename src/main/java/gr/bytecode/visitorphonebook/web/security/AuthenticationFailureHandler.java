package gr.bytecode.visitorphonebook.web.security;

import gr.bytecode.visitorphonebook.services.BackOfficeService;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Spring Authentication Failed Login Handler
 * 
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
public class AuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	/**
	 * Injected BackOfficeService bean
	 */
	@Autowired
	BackOfficeService backOfficeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SimpleUrlAuthenticationFailureHandler
	 * #onAuthenticationFailure(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		// redirect to the original referrer
		String referrer = request.getHeader("Referer");

		String redirectUrl = backOfficeService.getBaseUrl();

		if (referrer != null && "".equals(referrer) == false) {

			URL url = new URL(referrer);

			redirectUrl = url.getProtocol() + "://" + url.getHost();

			int port = url.getPort();

			if (port > 0) {
				redirectUrl += ":" + url.getPort();
			}

			redirectUrl += url.getPath() + "?lgerr=1&slgb=true";

		}

		response.sendRedirect(redirectUrl);

	}

}
