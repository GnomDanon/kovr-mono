package ru.kovrochist.platform.mono.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private static final Pattern PATTERN = Pattern.compile("^7\\d{10}$");


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && PATTERN.matcher(value).find();
	}
}
