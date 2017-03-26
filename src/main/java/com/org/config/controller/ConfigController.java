package com.org.config.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ConfigController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> testEndPoint() {
        return ResponseEntity.ok().body("Success");
    }
}
