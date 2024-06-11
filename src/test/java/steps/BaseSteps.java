package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.Page;

import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertTrue;
import static utils.drivers.FirefoxWebDriver.driver;
import static utils.settings.Settings.baseUrl;

public class BaseSteps extends Page {

    @Given("I go to Shapermint")
    public void i_go_to() {
        driver.get(baseUrl);
    }

    @When("I enter the first product within the section {string}")
    public void i_enter_the_first_product_within_the_section() {
    }

    @And("I click on the {string} button and proceed to checkout from the cart")
    public void i_click_on_the_button_and_proceed_to_checkout_from_the_cart() {
    }

    @And("I complete email data and shipping address data with the following information")
    public void i_complete_email_data_and_shipping_address_data_with_the_following_information() {
    }

    @And("I complete credit card data with the following information")
    public void i_complete_credit_card_data_with_the_following_information() {
    }

    @Then("the shipping method {string} must be displayed")
    public void the_shipping_method_must_be_displayed() {
    }

    @Then("the message {string} within Payment Information section must be displayed")
    public void the_message_within_payment_information_section_must_be_displayed() {
    }

    @Then("the URL must contain {string}")
    public void the_url_must_contain() {
    }



}
