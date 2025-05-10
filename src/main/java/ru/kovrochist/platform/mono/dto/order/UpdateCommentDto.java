package ru.kovrochist.platform.mono.dto.order;

import lombok.Data;

@Data
public class UpdateCommentDto {

	private Long employeeDto;

	private String comment;
}
