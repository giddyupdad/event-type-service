Feature: Health check resource endpoint

    @Fast
    @dev @qa @prod
    Scenario: Create Microservice
    Given the service is running
    When GET is called
    Then the service reports healthy status