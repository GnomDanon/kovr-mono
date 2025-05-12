package ru.kovrochist.platform.mono.filter;

import lombok.Getter;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;
import ru.kovrochist.platform.mono.util.StringUtil;

import java.util.Map;

@Getter
public class OrderFilter extends Filter {

	public static final String STATUS = "status";
	public static final String DELIVERY_TYPE = "deliveryType";
	public static final String DISTRICT = "district";

	private final OrderStatus[] status;
	private final DeliveryType[] deliveryType;
	private final String[] district;

	public OrderFilter(Map<String, String> params) {
		super(params);

		String[] status = params.get(STATUS) == null ? new String[]{} : params.get(STATUS).split(StringUtil.SEPARATOR);
		String[] deliveryType = params.get(DELIVERY_TYPE) == null ? new String[]{} : params.get(DELIVERY_TYPE).split(StringUtil.SEPARATOR);
		String[] district = params.get(DISTRICT) == null ? new String[]{} : params.get(DISTRICT).split(StringUtil.SEPARATOR);

		OrderStatus[] orderStatus = new OrderStatus[status.length];
		DeliveryType[] orderDeliveryType = new DeliveryType[deliveryType.length];

		for (int i = 0; i < status.length; i++) {
			try {
				orderStatus[i] = OrderStatus.byLabel(status[i]);
			} catch (Exception ignored) {
			}
		}

		for (int i = 0; i < orderDeliveryType.length; i++) {
			try {
				orderDeliveryType[i] = DeliveryType.byLabel(deliveryType[i]);
			} catch (Exception ignored) {
			}
		}

		this.status = validate(orderStatus) ? orderStatus : null;
		this.deliveryType = validate(orderDeliveryType) ? orderDeliveryType : null;
		this.district = validate(district) ? district : null;
	}
}
