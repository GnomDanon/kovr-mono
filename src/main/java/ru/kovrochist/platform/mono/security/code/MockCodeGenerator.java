package ru.kovrochist.platform.mono.security.code;

public class MockCodeGenerator implements CodeGenerator {

	private static final String CODE = "7645";

	@Override
	public String generate() {
		return CODE;
	}
}
