package ru.kovrochist.platform.mono.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/course")
@Tag(name = "Курс")
public interface CourseApi {

	@Operation(summary = "Создание нового курса")
	@PostMapping("/create")
	ResponseEntity<String> create();

	@Operation(summary = "Получение информации о курсе")
	@GetMapping("/get")
	ResponseEntity<String> get();
}
