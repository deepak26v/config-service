package com.org.config;

import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import com.org.config.service.AppConfigService;
import com.org.config.service.impl.AppConfigServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;

/**
 * Tests for {@link AppConfigServiceImpl}.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppConfigServiceTest {

    @Autowired
    private AppConfigService appConfigService;

    @Before
    public void setup() {
        //Persist App Config
        appConfigService.saveAppConfig(
                new AppConfig("test-app", "v1", "{\"key1\" : \"value1\"}"));

        //Persist App Config
        appConfigService.saveAppConfig(
                new AppConfig("test-app", "v2", "{\"key2\" : \"value2\"}"));
    }

    @Test
    public void fetchAppConfig() {
        //Fetch the AppConfig
        AppConfig appConfig = appConfigService.getAppConfig(new AppConfigKey("test-app", "v1"));

        assertNotNull(appConfig);
        assertEquals("{\"key1\" : \"value1\"}", appConfig.getAppConfigJson());
    }

    @Test
    public void fetchAllAppConfig() {
        List<AppConfig> appConfigList = appConfigService.getAppConfigs("test-app");

        assertNotNull(appConfigList);
        assertEquals(2, appConfigList.size());
        assertEquals("{\"key2\" : \"value2\"}", appConfigList.get(0).getAppConfigJson());
        assertEquals("{\"key1\" : \"value1\"}", appConfigList.get(1).getAppConfigJson());
    }

    @Test
    public void getAppConfigForMissingAppOrVersion() {
        AppConfig appConfig = appConfigService.getAppConfig(new AppConfigKey("test-app", "v10"));
        assertNull(appConfig);
    }
}
