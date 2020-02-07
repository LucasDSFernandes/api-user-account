package com.lucasdsf.api.user.domains.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbg_usuario")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario")
	private Long id;
	
	@Column(name="primeiro_nome", nullable = false)
	private String firstName;
	
	@Column(name="nome_completo", nullable = false)
	private String fullName;

	@Column(name="nm_login", nullable = false)
	private String userLogin;
	
	@Column(name="email", nullable = false)
	private String email;
	
	@Column(name="cd_senha", nullable = false)
	private String password;
	
	@OneToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "id_perfil")
	private ProfileUser profile;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ProfileUser getProfile() {
		return profile;
	}
	public void setProfile(ProfileUser profile) {
		this.profile = profile;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}