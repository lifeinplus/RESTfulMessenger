package org.artem.lifeincode.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.artem.lifeincode.messenger.database.DatabaseClass;
import org.artem.lifeincode.messenger.model.Profile;

public class ProfileService {

	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("artem", new Profile(1L, "artem", "Artem", "Denisov"));
	}
	
	public List<Profile> getProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public void deleteProfile(String profileName) {
		profiles.remove(profileName);
	}
	
}
