package com.farplayground.searchspecification.service;

import com.farplayground.searchspecification.model.response.ProfilesResponse;
import org.springframework.data.domain.Pageable;


/**
 * @author farras
 * @since 0.0.1
 */
public interface ProfileService {

    void createNewProfile(String name, Integer age);

    ProfilesResponse getAllProfile();

    ProfilesResponse getAllProfile(String searchKeyword, Pageable pageable);
}
