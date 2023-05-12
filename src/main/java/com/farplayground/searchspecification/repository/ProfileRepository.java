package com.farplayground.searchspecification.repository;

import com.farplayground.searchspecification.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author farras
 * @since 0.0.1
 */
public interface ProfileRepository {

    void save(Profile profile);

    List<Profile> getAllProfile();

    Page<Profile> findAllProfiles(String searchKeyword, Pageable pageable);
}
