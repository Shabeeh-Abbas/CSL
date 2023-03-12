package com.example.demo.role;

import java.util.Set;

import com.example.demo.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Roles_table")
@Entity
public class Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="Role")
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}	
//	@OneToMany(mappedBy="userRole")
//	private Set<UserEntity> users;
//
//
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
	
//
//	public Set<UserEntity> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<UserEntity> users) {
//		this.users = users;
//	}
//
//}
