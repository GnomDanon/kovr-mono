package ru.kovrochist.platform.mono.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.type.Gender;
import ru.kovrochist.platform.mono.type.Role;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Clients implements User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "code")
	private String code;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;

	@Column(name = "district")
	private String district;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "avatar")
	private byte[] avatar;

	@Column(name = "status")
	private String status;

	@Column(name = "created_at")
	private Date createdAt;

	public Role getRole() {
		return Role.CLIENT;
	}

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private List<Orders> orders;
}
