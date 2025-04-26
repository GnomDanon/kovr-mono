package ru.kovrochist.platform.mono.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kovrochist.platform.mono.dto.employee.role.CreateRoleDto;
import ru.kovrochist.platform.mono.dto.employee.role.RoleDto;
import ru.kovrochist.platform.mono.entity.Role;
import ru.kovrochist.platform.mono.exception.DoesNotExistException;
import ru.kovrochist.platform.mono.exception.employee.RoleDoesNotExistException;
import ru.kovrochist.platform.mono.mapper.employee.EmployeeMapper;
import ru.kovrochist.platform.mono.repository.RoleRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleDto create(CreateRoleDto roleDto) {
		Role role = roleRepository.findByName(roleDto.getName()).orElse(null);

		if (role != null)
			return EmployeeMapper.map(role);

		role = roleRepository.save(EmployeeMapper.map(roleDto));
		return EmployeeMapper.map(role);
	}

	public RoleDto getById(UUID id) throws DoesNotExistException {
		Role role = roleRepository.findById(id).orElseThrow(() -> new RoleDoesNotExistException(id));
		return EmployeeMapper.map(role);
	}
}
