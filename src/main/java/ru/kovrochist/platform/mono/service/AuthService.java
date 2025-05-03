package ru.kovrochist.platform.mono.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public String auth(String phone) {
		return "ok";
	}

	public String verify(String phone, String code) {
		return "ok";
	}

	public String logout() {
		return "ok";
	}
}
