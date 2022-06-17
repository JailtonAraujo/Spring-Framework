package com.br.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Entity
@Data
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String senha;

	private String login;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_role", uniqueConstraints = @UniqueConstraint(
				columnNames = {"usuario_id","role_id"}, name = "unique_user"),
		joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id", table = "usuario", unique = false,
				foreignKey = @ForeignKey(name="fk_usuariorole_usuario", value = ConstraintMode.CONSTRAINT)),
		
		inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id", table = "role", unique = false,
				foreignKey = @ForeignKey(name="fk_usuariorole_role", value = ConstraintMode.CONSTRAINT)) 
	)
	List<Role> roles;

	@Override
	public Collection<Role> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
