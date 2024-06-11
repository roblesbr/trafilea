package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.BasePage;

public class ShapermintSteps extends BasePage {

    @Given("I go to Shapermint")
    public void goToShapermint() {
        navigateToBaseUrl();
        System.out.println("Shapermint");
    }

    @When("I enter the first product within the section Our Best Sellers")
    public void i_enter_the_first_product_within_the_section() {

        WebDriverWait waitPopup = new WebDriverWait(driver, 10);
        waitPopup.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_xxsoyodj_popup-lead-trafilea-popup\"]/div/div/span")));

        driver.findElement(By.xpath("//*[@id=\"_xxsoyodj_popup-lead-trafilea-popup\"]/div/div/span")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Best Sellers')]")).click();
        driver.findElement(By.xpath("//img[@alt='Before and After Shapermint Cami']")).click();
    }

    @And("I click on the add to cart button and proceed to checkout from the cart")
    public void i_click_on_the_button_and_proceed_to_checkout_from_the_cart() throws InterruptedException {
        Thread.sleep(3000);
        scrollDownAndClick();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"justuno_form\"]/div/div[2]/div[1]/div/div/div")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*/text()[normalize-space(.)='PROCEED TO CHECKOUT']/parent::*")).click();
        Thread.sleep(3000);
    }

    @And("I complete email data and shipping address data with the following information")
    public void i_complete_email_data_and_shipping_address_data_with_the_following_information() {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("qa.email@gmail.com");

        WebElement buyerNameField = driver.findElement(By.name("delivery.buyer_name"));
        buyerNameField.sendKeys("My Name");

        WebElement buyerLastNameField = driver.findElement(By.name("delivery.buyer_lastname"));
        buyerLastNameField.sendKeys("My Lastname");

        WebElement addressField = driver.findElement(By.id("address-delivery-autocomplete"));
        addressField.sendKeys("123 William Street");

        WebElement address2Field = driver.findElement(By.name("delivery.address2"));
        address2Field.sendKeys("apt 1");

        WebElement cityField = driver.findElement(By.name("delivery.city"));
        cityField.sendKeys("New York");

        // Select the state
        WebElement stateDropdown = driver.findElement(By.xpath("//button[@id='headlessui-listbox-button-3']/div"));
        stateDropdown.click();

        WebElement newStateOption = driver.findElement(By.id("headlessui-listbox-option-36"));
        newStateOption.click();

        WebElement zipField = driver.findElement(By.name("delivery.zip"));
        zipField.sendKeys("10038");

        WebElement phoneField = driver.findElement(By.name("delivery.phone"));
        phoneField.sendKeys("1234567890");

    }

    @And("I complete credit card data with the following information")
    public void i_complete_credit_card_data_with_the_following_information() {
        driver.findElement(By.name("cardnumber")).sendKeys("1234 1234 1234 1234");
        driver.findElement(By.xpath("//input[@value='Name Lastname']")).sendKeys("Name Lastname");
        driver.findElement(By.name("exp-date")).sendKeys("01 / 25");
        driver.findElement(By.name("cvc")).sendKeys("999");
        enter();
    }

    @Then("the shipping method {string} must be displayed")
    public void the_shipping_method_must_be_displayed() {

        WebElement shippingMethodLabel = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div[6]/div[1]/div/h6"));

        // Validate if the first element is available
        if (shippingMethodLabel.isDisplayed()) {
            System.out.println("The element was found: " + shippingMethodLabel.getText());
        } else {
            System.out.println("The element was not found: " + shippingMethodLabel.getText());
        }

        // Find the second element
        WebElement shippingOptionsLabel = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div[6]/div[2]/div/p"));
        // Validate if the second element is available
        if (shippingOptionsLabel.isDisplayed()) {
            System.out.println("The element was found: " + shippingOptionsLabel.getText());
        } else {
            System.out.println("The element was not found: " + shippingOptionsLabel.getText());
        }

    }

    @Then("the message {string} within Payment Information section must be displayed")
    public void the_message_within_payment_information_section_must_be_displayed() {

        // Find the element containing the message
        WebElement messageElement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div[8]/div/div/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/span"));
        // Get the text of the message
        String message = messageElement.getText();

        // Validate if the message is equal to the expected one
        if (message.equals("The card number is not valid.")) {
            System.out.println("The message is correct.");
        } else {
            System.out.println("The message is not the expected one.");
        }
    }

    @Then("the URL must contain {string}")
    public void the_url_must_contain() {
        // Get the current URL
        String currentUrl = driver.getCurrentUrl();
        // Perform assertion to verify if the URL contains "/hc/checkout/"
        Assert.assertTrue(currentUrl.contains("/hc/checkout/"), "The URL does not contain /hc/checkout/");
    }


}
