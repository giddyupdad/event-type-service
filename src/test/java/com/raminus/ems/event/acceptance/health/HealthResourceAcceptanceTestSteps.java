package com.raminus.ems.event.acceptance.health;

import com.raminus.ems.event.Application;
import com.raminus.ems.event.acceptance.BaseIntegrationTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class HealthResourceAcceptanceTestSteps extends BaseIntegrationTest {

    private ResponseEntity<Health> response;

    @Given("^the service is running$")
    public void ensureTheAPIServicesAreRunning() {

    }

    @When("^GET is called$")
    public void ensureCorrespondenceHealthAPIisCalledSuccessfully() throws Exception {
        String urlForPath = getUrlForPath("/events-service/actuator/health");
        response = getRestTemplate().getForEntity(urlForPath, Health.class);
    }

    @Then("^the service reports healthy status$")
    public void ensureHealthReportStatusIsUP() {
        assertThat(response.getBody().getStatus(), equalTo("UP"));
    }

    public static final class Health {

        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
