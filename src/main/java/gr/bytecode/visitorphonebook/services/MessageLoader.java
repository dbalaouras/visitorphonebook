package gr.bytecode.visitorphonebook.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Localized Message loader
 * 
 * @author Dimitrios Balaouras
 * @version 1.0
 * @since 1.0
 * @copyright Bytecode.gr 2013
 * 
 */
@Component
public class MessageLoader {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Default Constructor
	 */
	public MessageLoader() {
	}

	/**
	 * @param key
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getString(String key, String[] args, Locale locale) {

		return messageSource.getMessage(key, args, locale);
	}

	/**
	 * @param key
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getString(String key, String[] args) {

		Locale locale = LocaleContextHolder.getLocale();

		return messageSource.getMessage(key, args, locale);
	}

}
