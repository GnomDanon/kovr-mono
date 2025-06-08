package ru.kovrochist.platform.mono.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class Advice {

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<String> authenticationException(AuthenticationException exception) {
		return process(HttpStatus.FORBIDDEN, new Exception("Ошибка авторизации: " + exception.getMessage()));
	}

	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<String> resourceAccessException(ResourceAccessException exception) {
		return process(HttpStatus.FORBIDDEN, exception);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> unknownException(Exception exception) {
		return process(HttpStatus.INTERNAL_SERVER_ERROR, exception);
	}

	@ExceptionHandler(DoesNotExistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> doesNotExistException(DoesNotExistException exception) {
		return process(HttpStatus.NOT_FOUND, exception);
	}

	@ExceptionHandler(ResourceConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<String> alreadyExistException(ResourceConflictException exception) {
		return process(HttpStatus.CONFLICT, exception);
	}

	@ExceptionHandler(IncorrectRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> incorrectRequestException(IncorrectRequestException exception) {
		return process(HttpStatus.BAD_REQUEST, exception);
	}

	private ResponseEntity<String> process(HttpStatus status, Exception exception) {
		exception.printStackTrace();
		return ResponseEntity.status(status).body(exception.getMessage());
	}
}
