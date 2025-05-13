package ru.kovrochist.platform.mono.dto.order;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateCommentDto {

	private Long employeeId;

	private String comment;
}
