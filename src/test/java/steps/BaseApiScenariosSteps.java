package steps;

import apis.BaseApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseApiScenariosSteps {

    private Response response;
    private List<Map<String, Object>> users;
    private Map<String, Object> firstActiveUser;
    private Map<String, Object> firstUser;

    @Given("^I send a GET request to \"([^\"]*)\"$")
    public void sendGetRequest(String url) {
        response = BaseApiTest.sendGetRequest(url);
        System.out.println("Sent GET request to: " + url);
    }

    @When("^I get the list of users$")
    public void getListOfUsers() {
        users = response.jsonPath().getList("data");
        System.out.println("Got the list of users:");
        users.forEach(user -> System.out.println(user));
    }

    @Then("^I filter the active users$")
    public void filterActiveUsers() {
        users = users.stream()
                .filter(user -> "active".equals(user.get("status")))
                .collect(Collectors.toList());
        System.out.println("Filtered active users");
    }

    @Then("^I get the details of the first active user$")
    public void getDetailsOfFirstActiveUser() {
        if (!users.isEmpty()) {
            firstActiveUser = users.get(0);
            System.out.println("Got details of the first active user:");
            System.out.println(firstActiveUser);
        } else {
            throw new RuntimeException("No active users found");
        }
    }

    @Then("^the user status should be \"([^\"]*)\"$")
    public void userStatusShouldBe(String status) {
        Assert.assertEquals(firstActiveUser.get("status"), status);
        System.out.println("User status is: " + status);
    }

    @Then("^the response status code should be (\\d+)$")
    public void responseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
        System.out.println("Response status code is: " + statusCode);
    }

    @Then("^I get the details of the first user$")
    public void getDetailsOfFirstUser() {
        if (!users.isEmpty()) {
            firstUser = users.get(0);
            System.out.println("Got details of the first user:");
            System.out.println(firstUser);
        } else {
            throw new RuntimeException("No users found");
        }
    }

    @Then("^I update the user's name to \"([^\"]*)\" with email \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void updateUserDetails(String name, String email, String status) {
        // Crear el cuerpo de la solicitud PATCH
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("status", status);

        System.out.println("Request Body for PATCH:");
        System.out.println(requestBody);

        // Llamar al método updateUser con el cuerpo de la solicitud
        BaseApiTest.updateUser(requestBody);
    }

    @Then("^the response name should be \"([^\"]*)\"$")
    public void responseNameShouldBe(String name) {
        Assert.assertEquals(response.jsonPath().getString("data.name"), name);
        System.out.println("Response name is: " + name);
    }
}
