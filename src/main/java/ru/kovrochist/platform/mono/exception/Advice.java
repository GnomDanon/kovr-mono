package ru.kovrochist.platform.mono.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {

	@ExceptionHandler(DoesNotExistException.class)
	public ResponseEntity<String> doesNotExistException(DoesNotExistException exception) {
		return process(HttpStatus.NOT_FOUND, exception);
	}

	@ExceptionHandler(ResourceConflictException.class)
	public ResponseEntity<String> alreadyExistException(ResourceConflictException exception) {
		return process(HttpStatus.CONFLICT, exception);
	}

	@ExceptionHandler(IncorrectRequestException.class)
	public ResponseEntity<String> incorrectRequestException(IncorrectRequestException exception) {
		return process(HttpStatus.BAD_REQUEST, exception);
	}

	private ResponseEntity<String> process(HttpStatus status, Exception exception) {
		return ResponseEntity.status(status).body(exception.getMessage());
	}
}
