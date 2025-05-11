package ru.kovrochist.platform.mono.entity;

import ru.kovrochist.platform.mono.type.Role;

public interface User {
	Long getId();
	String getPhone();
	String getCode();
	Role getRole();
}
