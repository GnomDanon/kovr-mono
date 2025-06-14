package ru.kovrochist.platform.mono.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.type.Gender;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Date;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employees implements User{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "phone")
	private String phone;

	@Column(name = "code")
	private String code;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "avatar")
	private byte[] avatar;

	@Column(name = "on_shift")
	private Boolean onShift;

	@Column(name = "status")
	private String status;

	@Column(name = "created_at")
	private Date createdAt;
}
