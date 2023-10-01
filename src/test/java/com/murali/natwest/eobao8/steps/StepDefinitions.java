package com.murali.natwest.eobao8.steps;

import com.murali.natwest.eobao8.data.AccountTypes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class StepDefinitions {

    @LocalServerPort
    private String port;

    private Exception exception;

    private ResponseEntity<AccountTypes> accountTypes;

    @Given("As a new customer")
    public void serviceIsUp() {
        log.info("serviceIsUp");
        // No-op SpringIntegrationTest class boots up Spring boot application
    }

    @When("I want to see all available accounts")
    public void getAllAccounts() {
        log.info("getAllAccounts");
        accountTypes = new RestTemplate()
                .getForEntity(serverUrl() + "/available",
                        AccountTypes.class);
    }

    @When("I want to see available accounts for {string} customers")
    public void getAccountsFor(String customerType) {
        log.info("getAccountsFor {}", customerType);
        try {
            accountTypes = new RestTemplate()
                    .getForEntity(serverUrl() + "/available-for/" + customerType,
                            AccountTypes.class);
        } catch (Exception e) {
            exception = e;
            log.info("getAccountsFor threw Exception {}", e.getClass().getSimpleName());
        }
    }

    @Then("then see a successful response")
    public void getResultsOkResponse() {
        log.info("getResultsOkResponse");
        Assertions.assertEquals(HttpStatus.OK, accountTypes.getStatusCode());
    }

    @Then("then see a failure response")
    public void getResultsFailResponse() {
        log.info("getResultsFailResponse");
        Assertions.assertNotNull(exception.getClass());
        Assertions.assertTrue(
                exception instanceof HttpServerErrorException.InternalServerError
        );
        Assertions.assertTrue(exception.getMessage().contains("No enum constant com.murali.natwest.eobao8.data.CustomerType.UNKNOWN"));
    }

    @Then("I should get a total of {int} account types")
    public void getResultsAccountTypesCount(int count) {
        log.info("getResultsAccountTypesCount");
        Assertions.assertEquals(count, accountTypes.getBody().getAccountTypes().size());
    }

    private String serverUrl() {
        String serverUrl = "http://localhost:" + port + "/app-open";
        log.info("Server URL is {}", serverUrl);
        return serverUrl;
    }

}
