package com.farplayground.searchspecification.repository;

import com.farplayground.searchspecification.domain.Profile;

import java.util.List;

/**
 * @author farras
 * @since 0.0.1
 */
public interface ProfileRepository {

    void save(Profile profile);

    List<Profile> getAllProfile();
}
