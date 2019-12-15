package com.raminus.ems.event.app;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Configuration
@Profile({"local"})
public class WireMockConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(WireMockConfiguration.class);

    private static final int PORT = 5343;
    private static final double AVERAGE_RESPONSE_TIME_OAUTH2 = 0.4;

    @Bean(name = "eventTypeWireMockServer", initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();
        stubForOauthCall(wireMockServer);
        LOG.info("eventTypeWireMockServer server started at port number {}", wireMockServer.port());
        return wireMockServer;
    }

    private void stubForOauthCall(WireMockServer wireMockServer) {

        wireMockServer.stubFor(post(urlPathMatching("/oauth2/token"))
            .willReturn(aResponse()
            .withStatus(HttpStatus.OK.value())
            .withFixedDelay((int) AVERAGE_RESPONSE_TIME_OAUTH2)
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
            .withBodyFile("src/test/resources/wiremock/response/oAuthResponse.json")));
    }
}
