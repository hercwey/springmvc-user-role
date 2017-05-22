package com.hlv.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "USERS")
@SequenceGenerator(name = "usersequence", sequenceName = "USER_SEQ", allocationSize = 1)
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersequence")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "USERNAME", length = 50, unique = true)	
	private String username;

	@Column(name = "PASSWORD", length = 255)
	private String password;
	
	@Column(name = "FULLNAME", length = 255)
	private String fullname;

	@ManyToMany(cascade=CascadeType.DETACH, fetch=FetchType.LAZY) //delete user and user_role, not delete role	
	@JoinTable(name = "USER_ROLE", 
		joinColumns = { @JoinColumn(name = "USER_ID",  nullable = false) }, 
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID",  nullable = false) })
	private Set<Roles> roles;// = new HashSet<Roles>(0);

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	//sort by id
	//@OrderBy("id DESC")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	@Override
	public String toString() {
		return String.format("{\"id\":%d,\"username\":\"%s\",\"fullname\":\"%s\"}", id, username, fullname);
	}
	
}
