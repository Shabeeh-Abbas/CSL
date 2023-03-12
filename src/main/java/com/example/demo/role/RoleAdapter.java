package com.example.demo.role;

public class RoleAdapter{
	
	public RoleDto roleDaoToDto(Roles rdao) {
		RoleDto rdto = new RoleDto();
		rdto.setRole(rdao.getRole());
		return rdto;
	}
	
	public Roles roleDtoToDao(RoleDto rdto) {
		Roles rdao = new Roles();
		rdao.setRole(rdto.getRole());
		return rdao;
	}
}