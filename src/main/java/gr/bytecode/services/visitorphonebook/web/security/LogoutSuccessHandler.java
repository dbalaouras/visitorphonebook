package gr.bytecode.services.visitorphonebook.web.security;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Spring Authentication Success Logout Handler 
 * 
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
public class LogoutSuccessHandler extends SimpleUrlAuthenticationFailureHandler {

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

		if (referrer != null && "".equals(referrer) == false) {

			URL url = new URL(referrer);

			URL redirectUrl = new URL(url.getProtocol() + "://" + url.getHost()
					+ ":" + url.getPort() + url.getPath()
					+ "?lgerr=1&slgb=true");

			response.sendRedirect(redirectUrl.toString());
		}

	}

}
