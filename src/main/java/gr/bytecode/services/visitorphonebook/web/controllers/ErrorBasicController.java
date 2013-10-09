package gr.bytecode.services.visitorphonebook.web.controllers;

import gr.bytecode.services.visitorphonebook.services.BackOfficeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Spring MVC Controller class for the backoffice of the Phonebook project
 * 
 * @author Dimitrios Balaouras
 * @since Jun 12, 2013 - 9:47:53 AM
 * @Copyright CA Inc. 2013
 * 
 */
@Controller
public class ErrorBasicController {

	@Autowired
	BackOfficeService backOfficeService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/404")
	public String httpCode404() {

		// TODO do some error logging

		return "404";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/50{[0-9]}")
	public String httpCode500() {

		// TODO do some error logging

		return "50x";
	}
}