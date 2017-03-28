package com.org.config;

import com.org.config.controller.AppConfigController;
import com.org.config.service.AppConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link AppConfigController}.
 */

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class AppConfigControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppConfigService appConfigService;

    @Test
    public void saveAppConfig() throws Exception {
        this.mockMvc.perform(post("/api/test-app/config/v1").content("{\"key1\" : \"value1\"}"));
    }

    @Test
    public void getAppConfigForWrongAppCodeOrversion() throws Exception {
        this.mockMvc.perform(get("/api/test-app/config/v2")).andExpect(status().isNotFound());
    }

}
