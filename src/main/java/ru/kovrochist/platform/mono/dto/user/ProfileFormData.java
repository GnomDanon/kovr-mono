package ru.kovrochist.platform.mono.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class ProfileFormData {

	private String firstName;

	private String lastName;

	private String phone;

	private Date birthday;

	private String gender;

	private String city;

	private String address;
}
