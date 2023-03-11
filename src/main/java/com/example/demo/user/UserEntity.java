package com.example.demo.user;

import java.util.Set;

import com.example.demo.Team.TeamEntity;
import com.example.demo.role.Roles;

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
	
	@ManyToMany(cascade= {CascadeType.MERGE, CascadeType.PERSIST} ,fetch = FetchType.LAZY)
	@JoinTable(
			name="User_Role_table",
			joinColumns= @JoinColumn(name="UID"),
			inverseJoinColumns = @JoinColumn(name="RID"))
	private Set<Roles> userRoles;
	
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

	public Set<Roles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Roles> userRoles) {
		this.userRoles = userRoles;
	}
	
}
