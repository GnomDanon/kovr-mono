package ru.kovrochist.platform.mono.filter;

import lombok.Getter;
import lombok.Setter;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.Map;

@Getter
public class OrderFilter extends Filter {

	public static final String STATUS = "status";
	public static final String DELIVERY_TYPE = "deliveryType";
	public static final String DISTRICT = "district";

	private final OrderStatus status;
	private final DeliveryType deliveryType;
	private final String district;

	public OrderFilter(Map<String, String> params) {
		super(params);

		String status = params.get(STATUS);
		String deliveryType = params.get(DELIVERY_TYPE);
		String district = params.get(DISTRICT);

		OrderStatus orderStatus;
		DeliveryType orderDeliveryType;

		try {
			orderStatus = OrderStatus.byLabel(status);
		} catch (Exception e) {
			orderStatus = null;
		}

		try {
			orderDeliveryType = DeliveryType.byLabel(deliveryType);
		} catch (Exception e) {
			orderDeliveryType = null;
		}

		this.status = orderStatus;
		this.deliveryType = orderDeliveryType;
		this.district = district == null ? "" : district;
	}
}
