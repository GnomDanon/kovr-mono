package ru.kovrochist.platform.mono.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.user.ProfileFormData;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.User;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.client.ClientDoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.EmployeeDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.client.ClientMapper;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.service.ClientService;
import ru.kovrochist.platform.mono.service.EmployeeService;
import ru.kovrochist.platform.mono.type.Role;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final ClientService clientService;
	private final EmployeeService employeeService;

	private final ru.kovrochist.platform.mono.security.user.User USER;

	@Override
	public CommonUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = employeeService.getByPhone(username);
		if (user == null)
			user = clientService.getByPhone(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return new CommonUserDetails(user);
	}

	public UserDto findUser(String phone) {
		Employees employee = employeeService.getByPhone(phone);
		if (employee != null) {
			return EmployeeMapper.mapToUser(employee);
		}
		Clients client = clientService.getByPhone(phone);
		return ClientMapper.mapToUser(client);
	}

	public UserDto getProfile() throws ClientDoesNotExistException, EmployeeDoesNotExistException {
		Long id = USER.getId();
		if (USER.getRole() == Role.CLIENT)
			return ClientMapper.map(clientService.getById(id));
		return EmployeeMapper.mapToUser(employeeService.getById(id));
	}

	public UserDto updateProfile(ProfileFormData profile) throws DoesNotExistException {
		Long id = USER.getId();
		if (USER.getRole() == Role.CLIENT)
			return ClientMapper.map(clientService.update(id, profile.getPhone(), profile.getFirstName(), profile.getLastName(), profile.getBirthday(), profile.getCity(), profile.getAddress(), profile.getGender()));
		return EmployeeMapper.mapToUser(employeeService.update(id, profile.getFirstName(), profile.getLastName(), profile.getBirthday(), profile.getPhone(), profile.getGender()));
	}
}
