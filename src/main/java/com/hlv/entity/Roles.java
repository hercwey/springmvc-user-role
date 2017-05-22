package com.hlv.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = "rolesequence", sequenceName = "ROLE_SEQ", allocationSize = 1)
public class Roles {

	@Id
	@Column(name = "ID")
	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesequence")
	private Long id;

	@Column(name = "CODE", length = 50)
	private String code;

	@Column(name = "NAME", length = 255)
	private String name;

	@ManyToMany(mappedBy = "roles")	
	private Set<Users> users = new HashSet<Users>(0);

	public Set<Users> getUsers() {
		return this.users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
