package com.lucasdsf.api.user.builder;

import com.lucasdsf.api.user.domains.entity.ProfileUser;
import com.lucasdsf.api.user.enums.ProfileEnums;

public class ProfileUserBuilder {
	
	private ProfileEnums profile;
	
	public static ProfileUserBuilder userDTOBuilder() {
		return new ProfileUserBuilder();
	}
	
	public ProfileUserBuilder profile(ProfileEnums profile) {
		this.profile = profile;
		return this;		
	}

	public ProfileUser build() {
		ProfileUser profileUser = new ProfileUser();
		profileUser.setDescriptionProfile(profile);
		return profileUser;
	} 

}
