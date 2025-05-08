package ru.kovrochist.platform.mono.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.user.UserDto;
import ru.kovrochist.platform.mono.entity.Clients;
import ru.kovrochist.platform.mono.entity.Employees;
import ru.kovrochist.platform.mono.entity.User;
import ru.kovrochist.platform.mono.mapper.client.ClientMapper;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.service.ClientService;
import ru.kovrochist.platform.mono.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final ClientService clientService;
	private final EmployeeService employeeService;

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
			return EmployeeMapper.map(employee);
		}
		Clients client = clientService.getByPhone(phone);
		return ClientMapper.map(client);
	}
}
