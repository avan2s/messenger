package org.andy.javabrains.messenger.service;

import org.andy.javabrains.messenger.database.DatabaseClass;
import org.andy.javabrains.messenger.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andy on 29.01.2017.
 */
public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getProfiles() {
        return new ArrayList<Profile>(this.profiles.values());
    }

    public Profile getProfile(String profileName) {
        return this.profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        int newId = this.profiles.size() + 1;
        profile.setId(newId);
        this.profiles.put(profile.getProfileName(), profile);
        return profile;
    }


    public Profile updateProfile(Profile profile) {
        boolean invalidProfileName = profile.getProfileName().isEmpty() || !this.profiles.containsKey(profile.getProfileName());

        if (invalidProfileName) {
            return null;
        }
        this.profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return this.profiles.remove(profileName);
    }
}
