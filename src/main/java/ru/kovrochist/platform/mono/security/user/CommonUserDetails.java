package ru.kovrochist.platform.mono.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kovrochist.platform.mono.entity.User;

import java.util.Collection;
import java.util.List;

public record CommonUserDetails(User user) implements UserDetails {
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return user.getCode();
	}

	@Override
	public String getUsername() {
		return user.getPhone();
	}
}
