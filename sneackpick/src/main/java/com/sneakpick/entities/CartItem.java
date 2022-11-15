package com.sneakpick.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "cart_item")
@Entity
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	private int qty;

	private String size;

	@Temporal(TemporalType.DATE)
	private Date dateStart;
	@Temporal(TemporalType.DATE)
	private Date dateEnd;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = true)
	@JsonBackReference
	private Order order;

//	public boolean canUpdateQty(Integer qty) {
//		return qty == null || qty <= 0 || this.getProduct().hasStock(qty);
//	}

//	public BigDecimal getSubtotal() {
//		return new BigDecimal(product.getPrice()).multiply(new BigDecimal(qty));
//	}

//	public void addQuantity(int qty) {
//		if (qty > 0) {
//			this.qty = this.qty + qty;
//		}
//	}

	public boolean hasSameSizeThan(String size2) {
		return this.size.equals(size2);
	}

}