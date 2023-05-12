package com.farplayground.searchspecification.impl;

import com.farplayground.searchspecification.domain.Profile;
import com.farplayground.searchspecification.model.response.ProfilesResponse;
import com.farplayground.searchspecification.repository.ProfileRepository;
import com.farplayground.searchspecification.service.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        final List<Profile> allProfile = profileRepository.getAllProfile();

        return ProfilesResponse.from(allProfile);
    }

    @Override
    public ProfilesResponse getAllProfile(String searchKeyword, Pageable pageable) {
        final Page<Profile> allProfiles = profileRepository.findAllProfiles(searchKeyword, pageable);

        return ProfilesResponse.from(allProfiles.getContent(), allProfiles.getTotalElements(), allProfiles.getTotalPages());
    }
}
