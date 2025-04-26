package ru.kovrochist.platform.mono.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.kovrochist.platform.mono.api.EventApi;
import ru.kovrochist.platform.mono.service.EventService;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

	private final EventService eventService;

	@Override
	public ResponseEntity<String> get() {
		return ResponseEntity.ok(eventService.get());
	}

	@Override
	public ResponseEntity<String> create() {
		return ResponseEntity.ok(eventService.create());
	}
}