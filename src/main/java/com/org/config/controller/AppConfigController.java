package com.org.config.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.config.exception.AppConfigNotFoundException;
import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import com.org.config.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class AppConfigController {

    public static final String BASE_PATH = "/api";

    private static final String CONFIG = "/config";

    public static final String APPCODE = "/{appcode}";

    public static final String VERSION = "/{version}";

    @Autowired
    private AppConfigService appConfigService;

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + APPCODE + CONFIG + VERSION)
    @ResponseBody
    public ResponseEntity<?> getAppConfig(@PathVariable String appcode, @PathVariable String version) {
        try{
            AppConfig appConfig = appConfigService.getAppConfig(new AppConfigKey(appcode, version));
            if(appConfig == null) {
                throw new AppConfigNotFoundException(appcode, version);
            } else {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(appConfig.getAppConfigJson());
            }
        } catch(AppConfigNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + APPCODE + CONFIG)
    @ResponseBody
    public ResponseEntity<?> getAppConfigs(@PathVariable String appcode) throws JsonProcessingException {
        try {
            List<AppConfig> appConfigList = appConfigService.getAllAppConfig(appcode);
            if(CollectionUtils.isEmpty(appConfigList)) {
                throw new AppConfigNotFoundException(appcode);
            } else {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ObjectMapper().writeValueAsString(appConfigList));
            }
        } catch(AppConfigNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred " + " => " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH + APPCODE + CONFIG + VERSION)
    @ResponseBody
    public ResponseEntity<?> createOrUpdateAppConfig(@PathVariable String appcode,
                                                     @PathVariable String version,
                                                     @RequestBody Map<String, Object> appConfigJson,
                                                     HttpServletRequest httpServletRequest) {
        try {
            AppConfig appConfig = new AppConfig(appcode,
                                                version,
                                                new ObjectMapper().writeValueAsString(appConfigJson));
            appConfigService.saveAppConfig(appConfig);
            URI locationURI = new URI(httpServletRequest.getRequestURL().toString() + "/");
            HttpHeaders headers = new HttpHeaders();
            return ResponseEntity.created(locationURI)
                                .body("Successfully created new JSON Config document");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create new JSON Config " + " => " + e.getMessage());
        }
    }
}

/*
@ControllerAdvice(annotations = RestController.class)
class AppConfigControllerAdvice {
    public ResponseEntity<VndErrors>
}*/
