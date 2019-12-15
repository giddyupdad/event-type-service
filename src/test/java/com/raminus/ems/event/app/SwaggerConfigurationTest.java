package com.raminus.ems.event.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigurationTest {

    @InjectMocks
    private SwaggerConfiguration swaggerConfiguration;

    @Test
    public void createApiDocket() {
        Docket docket = swaggerConfiguration.apiDocket();

        assertNotNull(docket);
        assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
    }

    @Test
    public void getApiInfo() {
        ApiInfo apiInfo = swaggerConfiguration.buildApiInfo();
        assertEquals("Event Type Service", apiInfo.getTitle());
        assertEquals("Event Management Service API", apiInfo.getDescription());
        assertEquals("V1.0", apiInfo.getVersion());
        assertEquals("Works only in DEV. Disabled in QA and PROD", apiInfo.getTermsOfServiceUrl());
        assertEquals("Apache 2.0", apiInfo.getLicense());
        assertEquals("https://www.apache.org/licenses/LICENSE-2.0", apiInfo.getLicenseUrl());

        Contact contact = apiInfo.getContact();
        assertEquals("raminus team", contact.getName());
        assertEquals("www.raminus.com", contact.getUrl());
        assertEquals("contact@raminus.com", contact.getEmail());
    }
}