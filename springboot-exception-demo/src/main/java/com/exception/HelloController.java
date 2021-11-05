package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.model.Account;

@RestController
public class HelloController {
	
	Logger logger = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping("/api/accounts/{accountId}")
	public ResponseEntity<Account> getAccounts(@PathVariable("accountId") String accountId) {

		// hard coded output

		Account account = new Account();
		account.setAccountId(accountId);
		account.setName("Successful");

		if (accountId.equals("1")) {
			throw new IllegalArgumentException();
		}

		if (accountId.equals("2")) {
			throw new IllegalStateException();
		}

		logger.debug("The given argument is valid");
		// response
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}

	@ExceptionHandler(value = { IllegalStateException.class })
	protected ResponseEntity<Object> handleException(IllegalStateException e) {
		logger.error("Illegal Argument Exception in Controller level");
		return new ResponseEntity<Object>("illegal state exception in controller", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleException(IllegalArgumentException e) { 
		logger.error("Illegal Argument Exception in Controller level");
		return new ResponseEntity<Object>("illegal arg exception in controller", HttpStatus.BAD_REQUEST);
	}

}
