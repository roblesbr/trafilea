package apis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static utils.settings.Settings.*;

public class BaseApiTest {

    private static Response response;
    private static RequestSpecification httpRequest;
    private static String strResponse;
    private static String firstUserId;

    public static void setBaseUri() {
        RestAssured.baseURI = baseUrl;
    }

    public static RequestSpecification authTwitter() {
        httpRequest = given().auth().oauth(consumerKey, consumerSecret, accessToken, accessTokenSecret);
        return httpRequest;
    }

    public static Response sendGetRequest(String url) {
        return given().get(url).then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON).extract().response();
    }

    public static Response updateUser(Map<String, Object> user) {
        // Realizar la solicitud GET para obtener el ID del primer usuario
        Response getResponse = given()
                .header("Authorization", bearerToken) // Bearer Token
                .get("https://gorest.co.in/public/v1/users")
                .then().extract().response();

        // Verificar el código de estado de la respuesta GET
        int statusCode = getResponse.getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("Error al obtener la lista de usuarios. Código de estado: " + statusCode);
        }

        // Obtener el ID del primer usuario
        firstUserId = getResponse.jsonPath().getString("data[0].id");

        // Imprimir el ID para verificar
        System.out.println("El ID del primer usuario es: " + firstUserId);

        // Realizar la solicitud PATCH utilizando el ID del primer usuario
        Response patchResponse = given()
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .body(user)
                .patch("https://gorest.co.in/public/v1/users/" + firstUserId)
                .then().assertThat().statusCode(200).extract().response();

        // Imprimir el cuerpo de respuesta del servidor
        System.out.println("Respuesta del servidor:");
        System.out.println(patchResponse.getBody().asString());

        // Devolver la respuesta de la solicitud PATCH
        return patchResponse;
    }




}
