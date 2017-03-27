package com.org.config.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/api")
public class ConfigController {

    public static final String BASE_PATH = "/api";

    public static final String APPCODE_CONFIG = "{appcode}/config";

    public static final String VERSION = "{version}";

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + APPCODE_CONFIG + VERSION)
    @ResponseBody
    public ResponseEntity<?> getAppConfig() {
        return ResponseEntity.ok().body("Success");
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + APPCODE_CONFIG)
    @ResponseBody
    public ResponseEntity<?> getAppConfigs() {
        return ResponseEntity.ok().body("Success");
    }

    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH + APPCODE_CONFIG + VERSION)
    @ResponseBody
    public ResponseEntity<?> createOrUpdateAppConfig() {
        return ResponseEntity.ok().body("Success");
    }
}