package com.sneakpick.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "product")
public class Product {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	private String name;

	private String description;

	@ManyToOne
	private Brand brand;

	private double price;

	@Lob
	private byte[] image;
	@Lob
	private byte[] image2;
	@Lob
	private byte[] image3;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Size> sizes;

//	@Temporal(TemporalType.DATE)
//	private Date pubDate;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date promoDateStart;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date promoDateEnd;

	@Override
	public String toString() {
		return name + "- " + brand.getName() + "-" + price;
	}

}
