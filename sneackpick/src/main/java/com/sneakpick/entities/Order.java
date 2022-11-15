package com.sneakpick.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date orderDate;

	@Temporal(TemporalType.DATE)
	private Date dateStart;
	@Temporal(TemporalType.DATE)
	private Date dateEnd;

	private Date shippingDate;

	private String orderStatus;

	private BigDecimal orderTotal;

	@OneToMany(mappedBy = "order")
	@JsonManagedReference
	private List<CartItem> cartItems;

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "shipping_id")
	private Shipping shipping;

	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@ManyToOne
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

}
