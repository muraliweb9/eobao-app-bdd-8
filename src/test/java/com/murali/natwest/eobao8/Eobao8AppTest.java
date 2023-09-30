package com.murali.natwest.eobao8;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        glue = "com.murali.natwest.eobao8.steps")
class Eobao8AppTest {


}
