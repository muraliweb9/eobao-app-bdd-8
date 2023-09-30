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
import org.springframework.web.client.RestTemplate;

@Slf4j
public class StepDefinitions {

    @LocalServerPort
    private String port;

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
        accountTypes = new RestTemplate()
                .getForEntity(serverUrl() + "/available-for/" + customerType,
                        AccountTypes.class);
    }

    @Then("then see a successful response")
    public void getResults_1() {
        log.info("getResults_1");
        Assertions.assertEquals(HttpStatus.OK, accountTypes.getStatusCode());
    }


    @Then("I should get a total of {int} account types")
    public void getResults_2(int count) {
        log.info("getResults_2");
        Assertions.assertEquals(count, accountTypes.getBody().getAccountTypes().size());
    }

    private String serverUrl() {
        String serverUrl = "http://localhost:" + port + "/app-open";
        log.info("Server URL is {}", serverUrl);
        return serverUrl;
    }

}
