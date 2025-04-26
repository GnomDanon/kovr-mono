package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.CourseApi;
import ru.kovrochist.platform.mono.service.CourseService;

@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApi {

	private final CourseService courseService;

	@Override
	public ResponseEntity<String> create() {
		return ResponseEntity.ok(courseService.create());
	}

	@Override
	public ResponseEntity<String> get() {
		return ResponseEntity.ok(courseService.get());
	}
}
