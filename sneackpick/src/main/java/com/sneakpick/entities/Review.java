package com.sneakpick.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "reveiw")
public class Review {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;

	@ManyToOne
	private Product product;

	@ManyToOne
	private User user;

	private int stars;
}
