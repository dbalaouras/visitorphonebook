package gr.bytecode.services.visitorphonebook.web.security;

import gr.bytecode.services.visitorphonebook.services.BackOfficeService;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * Spring Authentication Success Login Handler
 * 
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	/**
	 * Injected BackOfficeService bean
	 */
	@Autowired
	BackOfficeService backOfficeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * SimpleUrlAuthenticationSuccessHandler
	 * #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
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

			redirectUrl += url.getPath();

		}

		response.sendRedirect(redirectUrl);
	}

}
