package ru.kovrochist.platform.mono.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.kovrochist.platform.mono.type.DeliveryType;
import ru.kovrochist.platform.mono.type.OrderStatus;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "phone")
	private String phone;

	@Column(name = "city")
	private String city;

	@Column(name = "address")
	private String address;

	@Column(name = "district")
	private String district;

	@Column(name = "comment")
	private String comment;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	private Date updatedAt;

	@Column(name = "delivery_type")
	@Enumerated(EnumType.STRING)
	private DeliveryType deliveryType;

	@Column(name = "delivery_days")
	private String deliveryDays;

	@Column(name = "delivery_time_start")
	private Date deliveryTimeStart;

	@Column(name = "delivery_time_end")
	private Date deliveryTimeEnd;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column(name = "discount")
	private Double discount;

	@Column(name = "sources")
	private String sources;

	@Column(name = "price")
	private Integer price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Clients client;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderItems> items;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<AssignedEmployees> employees;
}
