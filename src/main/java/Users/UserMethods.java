package Users;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.given;

public class UserMethods {
    @Step("Send POST request to /api/auth/register")
    public static Response createUser(User user){
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    @Step("Send POST request to /api/auth/login")
    public static Response logInUser(User user){
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/login");

    }

    @Step("Get accessToken using GET request to /api/auth/login")
    public static String getAccessToken(User user){
        ResponseBody body =  logInUser(user).getBody();
        String token;
        try {
            token = body.path("accessToken").toString().substring(7);
        } catch (NullPointerException exception) {
            return null;
        }
        return token;
    }

    @Step("Send DELETE request to /api/auth/user")
    public static Response deleteUser(String token){
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .when()
                .delete("/api/auth/user");
    }
}
