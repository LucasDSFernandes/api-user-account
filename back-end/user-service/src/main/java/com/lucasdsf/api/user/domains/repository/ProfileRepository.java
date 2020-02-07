package com.lucasdsf.api.user.domains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.enums.ProfileEnums;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileUser, Long>{
	ProfileUser findByDescriptionProfile(ProfileEnums profile);
}
