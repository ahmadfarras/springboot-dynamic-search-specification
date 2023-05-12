package com.farplayground.searchspecification.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author farras
 * @since 0.0.1
 */

@RestController
@RequestMapping(value = "/hello", produces = MediaType.APPLICATION_JSON)
public class HelloController {

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> getHello(){
        Map<String, String> helloResponse = new HashMap<>();
        helloResponse.put("tes", "Hello World");

        return new ResponseEntity<>(helloResponse, HttpStatus.OK);
    }
}