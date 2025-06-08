package ru.kovrochist.platform.mono.exception;

public class ResourceAccessException extends Exception {
	public ResourceAccessException() {
		super("Отказано в доступе");
	}
}
