package com.raminus.ems.event.acceptance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class BaseIntegrationTest {

    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int embeddedTomcatPort;

    @Value("${atdd.serverUri.host:localhost}")
    private String host;
    @Value("${atdd.serverUri.scheme:http}")
    private String scheme;
    @Value("${atdd.serverUri.port:0}")
    private int port;


    public BaseIntegrationTest() {

    }

    public URI getUri() throws URISyntaxException {
        try {
            if (port == 0) {
                port = embeddedTomcatPort;
            }
            return new URI(scheme, null, host, port, null, null, null);
        } catch (URISyntaxException e) {
            throw e;
        }
    }

    public String getUrlForPath(String path) throws URISyntaxException {
        return UriComponentsBuilder.fromUri(getUri()).pathSegment(path).build().toUriString();
    }

    public TestRestTemplate getRestTemplate() {
        return restTemplate;
    }

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
