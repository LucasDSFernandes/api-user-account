package com.lucasdsf.api.user.domains.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lucasdsf.api.user.enums.ProfileEnums;

@Entity
@Table(name = "tbg_perfil_usuario")
public class ProfileUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_perfil")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_perfil", nullable = false)
	private ProfileEnums descriptionProfile;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProfileEnums getDescriptionProfile() {
		return descriptionProfile;
	}

	public void setDescriptionProfile(ProfileEnums descriptionProfile) {
		this.descriptionProfile = descriptionProfile;
	}


}
