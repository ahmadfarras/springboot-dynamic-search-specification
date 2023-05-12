package com.farplayground.searchspecification.controller;

import com.farplayground.searchspecification.model.request.CreateProfileRequest;
import com.farplayground.searchspecification.model.response.ProfilesResponse;
import com.farplayground.searchspecification.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getAllProfile(){
        ProfilesResponse response = profileService.getAllProfile();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
