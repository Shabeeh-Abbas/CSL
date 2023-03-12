package com.example.demo.user;

import java.util.Set;

import com.example.demo.Team.TeamEntity;


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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "Users_Table")
@Entity
public class UserEntity {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Username", unique = true)
	private String username;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Email", unique = true)
	private String email;
	
	@Column(name="Role")
	private String role;
	
	@OneToOne
	@JoinColumn(name="Team_id")
	private TeamEntity team;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return role;
	}

	public void setUserRole(String userRoles) {
		this.role = userRoles;
	}
	
}
