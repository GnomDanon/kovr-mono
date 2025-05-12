package ru.kovrochist.platform.mono.util;

public class StringUtil {

	public static String SEPARATOR = "&";

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty() || str.isBlank();
	}
}
