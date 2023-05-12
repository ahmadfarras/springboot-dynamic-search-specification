package com.farplayground.searchspecification.impl;

import com.farplayground.searchspecification.domain.Profile;
import com.farplayground.searchspecification.model.response.ProfilesResponse;
import com.farplayground.searchspecification.repository.ProfileRepository;
import com.farplayground.searchspecification.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author farras
 * @since 0.0.1
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public void createNewProfile(String name, Integer age) {
        Profile newProfile = Profile.createNewProfile(name, age);

        profileRepository.save(newProfile);
    }

    @Override
    public ProfilesResponse getAllProfile() {
        List<Profile> allProfile = profileRepository.getAllProfile();

        return ProfilesResponse.from(allProfile);
    }
}
