package com.sneakpick.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The persistent class for the user database table.
 * 
 */
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 65981149772133526L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "provider_user_id")
	private String providerUserId;

	private String email;

	@Column(name = "enabled", columnDefinition = "BIT", length = 1)
	private boolean enabled;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "created_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;

	private String password;

	private String provider;

	// bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;
}