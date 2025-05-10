package ru.kovrochist.platform.mono.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProfileFormData {

	private String firstName;

	private String lastName;

	private String phone;

	private String birthday;

	private String gender;

	private String city;

	private String address;
}
