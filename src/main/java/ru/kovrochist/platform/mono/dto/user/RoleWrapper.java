package ru.kovrochist.platform.mono.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleWrapper {

	private String value;
}
