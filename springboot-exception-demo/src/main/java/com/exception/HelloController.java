package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.model.Account;

@RestController
public class HelloController {

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

		// response
		return new ResponseEntity<Account>(account, HttpStatus.OK);

	}

	@ExceptionHandler(value = { IllegalStateException.class })
	protected ResponseEntity<Object> handleException(IllegalStateException e) {
		return new ResponseEntity<Object>("illegal state exception in controller", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleException(IllegalArgumentException e) {

		return new ResponseEntity<Object>("illegal arg exception in controller", HttpStatus.BAD_REQUEST);
	}

}
