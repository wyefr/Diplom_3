package helpers.api.user;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Random;

import static helpers.Urls.*;
import static io.restassured.RestAssured.given;

public class UserMethods {
    private static String accessToken;

    @Step("Create user")
    public static Response createUser(String email, String password, String name) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(new UserPojo(email, password, name))
                .when()
                .post(REGISTER_USER);
    }

    @Step("Login user")
    public static Response loginUser(String email, String password) {
        return given()
                .header("Content-Type", "application/json")
                .and()
                .body(new UserPojo(email, password))
                .when()
                .post(LOGIN);
    }

    @Step("Delete user")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(USER);
    }

    @Step("Creating test user")
    public static void createTestUser(String name, String email, String password) {
        accessToken = createUser(email, password, name).then().extract().path("accessToken").toString();
    }
}