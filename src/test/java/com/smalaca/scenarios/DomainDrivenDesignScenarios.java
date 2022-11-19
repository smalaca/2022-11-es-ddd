package com.smalaca.scenarios;

import com.smalaca.DomainDrivenDesign;
import com.smalaca.scenarios.jbehave.configuration.JBehaveConfiguration;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.assertj.core.api.Assertions.assertThat;

public class DomainDrivenDesignScenarios extends JBehaveConfiguration {
    private DomainDrivenDesign domainDrivenDesign;
    private boolean actual;

    @Given("Domain Driven Design")
    public void givenDDD() {
        domainDrivenDesign = new DomainDrivenDesign();
    }

    @When("checks if it helps")
    public void whenChecksIfItHelps() {
        actual = domainDrivenDesign.doesItHelp();
    }

    @Then("it should help")
    public void thenItShouldHelp() {
        assertThat(actual).isTrue();
    }
}
