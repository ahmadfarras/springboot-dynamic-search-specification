package com.farplayground.searchspecification.model.response;

import com.farplayground.searchspecification.domain.Profile;
import lombok.Builder;
import lombok.Data;

/**
 * @author farras
 * @since 0.0.1
 */
@Builder
@Data
public class ProfileDetailResponse {

    private final Integer id;

    private final String name;

    private final Integer age;

    public static ProfileDetailResponse from(Profile profile){
        return ProfileDetailResponse.builder()
                .id(profile.getId())
                .name(profile.getName())
                .age(profile.getAge())
                .build();
    }
}
