package ru.kovrochist.platform.mono.dto.order;

import lombok.Data;

import java.util.Date;

@Data
public class RescheduleDto {

	private String[] deliveryDays;

	private Date deliveryTimeStart;

	private Date deliveryTimeEnd;
}
