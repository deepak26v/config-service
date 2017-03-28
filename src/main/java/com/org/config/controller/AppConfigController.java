package com.org.config.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.config.exception.AppConfigNotFoundException;
import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import com.org.config.model.AppConfigResponse;
import com.org.config.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class AppConfigController {

    private static final String BASE_PATH = "/api";

    private static final String CONFIG = "/config";

    private static final String APPCODE = "/{appcode}";

    private static final String VERSION = "/{version:.+}";

    private static final String GET_APP_CONFIG_LINK = "GET-AppConfig";

    private static final String GET_APP_CONFIGS_LINK = "GET-AppConfigs";

    private static final String SUCCESS_MESSAGE = "Successfully created new JSON Config document";

    @Autowired
    private AppConfigService appConfigService;

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + APPCODE + CONFIG + VERSION)
    @ResponseBody
    public ResponseEntity<?> getAppConfig(@PathVariable String appcode, @PathVariable String version) {
        AppConfig appConfig = getConfig(appcode, version);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(appConfig.getAppConfigJson());
    }

    @RequestMapping(method = RequestMethod.GET, value = BASE_PATH + APPCODE + CONFIG)
    @ResponseBody
    public ResponseEntity<?> getAppConfigs(@PathVariable String appcode) {
        List<AppConfig> appConfigList = getConfigs(appcode);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(appConfigList);
    }

    @RequestMapping(method = RequestMethod.POST, value = BASE_PATH + APPCODE + CONFIG + VERSION)
    @ResponseBody
    public ResponseEntity<AppConfigResponse> createOrUpdateAppConfig(
                                                    @PathVariable String appcode,
                                                    @PathVariable String version,
                                                    @RequestBody Map<String, Object> appConfigJson,
                                                    HttpServletRequest httpServletRequest) throws JsonProcessingException {

        AppConfig appConfig = createAppConfig(appcode, version, appConfigJson);
        appConfigService.saveAppConfig(appConfig);
        AppConfigResponse appConfigResponse = new AppConfigResponse(SUCCESS_MESSAGE);

        //Add location header for other resources which a client can access
        HttpHeaders headers = buildHeaders(appcode, version);
        return new ResponseEntity<AppConfigResponse>(appConfigResponse, headers, HttpStatus.CREATED);
    }

    /**
     * Method to add headers containing links to other resources
     *
     * @param appcode
     * @param version
     * @return HttpHeaders
     */
    private HttpHeaders buildHeaders(String appcode, String version) {
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println();
        String uriOfGetConfigResource = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();

        URI uriOfGetConfigsResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                                    .path("/api/{appcode}/config")
                                                                    .buildAndExpand(appcode)
                                                                    .toUri();

        httpHeaders.set(GET_APP_CONFIG_LINK, uriOfGetConfigResource);
        httpHeaders.set(GET_APP_CONFIGS_LINK, uriOfGetConfigsResource.toString());

        return httpHeaders;
    }

    /**
     * Create a App Config instance
     *
     * @param appcode
     * @param version
     * @param appConfigJson
     * @return AppConfig
     * @throws JsonProcessingException
     */
    private AppConfig createAppConfig(@PathVariable String appcode,
                                      @PathVariable String version,
                                      @RequestBody Map<String, Object> appConfigJson) throws JsonProcessingException {
        return new AppConfig(appcode, version, new ObjectMapper().writeValueAsString(appConfigJson));
    }

    /**
     * Retrieve an app config or throw an exception if config is missing
     * @param appcode
     * @param version
     * @return AppConfig
     */
    private AppConfig getConfig(String appcode, String version) {
        AppConfig appConfig = appConfigService.getAppConfig(new AppConfigKey(appcode, version));
        if(appConfig == null) {
            throw new AppConfigNotFoundException(appcode, version);
        }
        return appConfig;
    }

    /**
     * Retrieve all config of a specific app
     *
     * @param appcode
     * @return List<AppConfig>
     */
    private List<AppConfig> getConfigs(@PathVariable String appcode) {
        List<AppConfig> appConfigList = appConfigService.getAppConfigs(appcode);
        if(CollectionUtils.isEmpty(appConfigList)) {
            throw new AppConfigNotFoundException(appcode);
        }
        return appConfigList;
    }
}
