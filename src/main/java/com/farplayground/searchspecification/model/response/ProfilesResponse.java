package com.farplayground.searchspecification.model.response;

import com.farplayground.searchspecification.domain.Profile;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author farras
 * @since 0.0.1
 */
@Value
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
public class ProfilesResponse extends ResponsePagination{

    List<ProfileDetailResponse> profiles;

    public static ProfilesResponse from(List<Profile> profiles){
        return ProfilesResponse.builder()
                .profiles(profiles.stream().map(ProfileDetailResponse::from).toList())
                .build();
    }

    public static ProfilesResponse from(List<Profile> profiles, Long totalData, Integer totalPage){
        return ProfilesResponse.builder()
                .profiles(profiles.stream().map(ProfileDetailResponse::from).toList())
                .totalData(totalData)
                .totalPage(totalPage)
                .build();
    }
}
