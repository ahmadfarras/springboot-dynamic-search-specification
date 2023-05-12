package com.farplayground.searchspecification.service;

import com.farplayground.searchspecification.model.response.ProfilesResponse;

/**
 * @author farras
 * @since 0.0.1
 */
public interface ProfileService {

    void createNewProfile(String name, Integer age);

    ProfilesResponse getAllProfile();
}
