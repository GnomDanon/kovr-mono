package ru.kovrochist.platform.mono.dto.metadata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Обертка вокруг типа справочника")
public class TypeWrapper {

	@Schema(description = "Тип справочника")
	private String type;
}
