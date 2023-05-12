package com.farplayground.searchspecification.controller;

import com.farplayground.searchspecification.model.query.Sort;
import com.farplayground.searchspecification.model.request.CreateProfileRequest;
import com.farplayground.searchspecification.model.response.ProfilesResponse;
import com.farplayground.searchspecification.service.ProfileService;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author farras
 * @since 0.0.1
 */
@RestController
@RequestMapping(value = "/profile")
@Validated
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON
    )
    public ResponseEntity<Object> createProfile(
            @Valid @RequestBody CreateProfileRequest request
    ){
        profileService.createNewProfile(request.getName(), request.getAge());

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON)
//    public ResponseEntity<Object> getAllProfile(){
//        ProfilesResponse response = profileService.getAllProfile();
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getAllProfileWithSearchFeatureAndPagination(
            @Valid @QueryParam("page") Integer page,
            @Valid @QueryParam("size") Integer size,
            @Valid @QueryParam("keyword") String searchKeyword,
            @Valid Sort sort
    ){
        final Pageable pageable = PageRequest.of(page, size, sort.getDirection(), sort.getProperty());

        ProfilesResponse response = profileService.getAllProfile(searchKeyword, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
