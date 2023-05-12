package com.farplayground.searchspecification.model.response;

import com.farplayground.searchspecification.domain.Profile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author farras
 * @since 0.0.1
 */
@Builder
@Data
public class ProfilesResponse {

    private final List<ProfileDetailResponse> profiles;

    public static ProfilesResponse from(List<Profile> profiles){
        return ProfilesResponse.builder()
                .profiles(profiles.stream().map(ProfileDetailResponse::from).toList())
                .build();
    }
}
