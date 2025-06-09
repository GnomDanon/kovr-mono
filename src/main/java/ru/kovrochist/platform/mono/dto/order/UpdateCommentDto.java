package ru.kovrochist.platform.mono.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос на обновление комментария")
public class UpdateCommentDto {

	@Schema(description = "Идентификатор сотрудника")
	private Long employeeId;

	@Schema(description = "Комментарий")
	private String comment;
}
