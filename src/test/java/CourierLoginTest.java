import base.CourierCreate;
import base.CourierLogin;
import config.Constants;
import config.Responses;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.DeleteCourierById;
import utils.StaticInput;

import static config.Constants.*;
import static org.hamcrest.Matchers.*;

public class CourierLoginTest {
    private Response loginResponse;
    private int courierId;
    private DeleteCourierById deleteCourier = new DeleteCourierById();

    @Before
    public void setUp(){
        RestAssured.baseURI= Constants.BASE_URL;
    }
    @Test
    @Step("Логинимся со всеми обязательными полями")
    public void courierLogInPositive() {
        CourierCreate courierCreate = StaticInput.staticCourierCreate();
        Responses.createCourierResponse(courierCreate);
        CourierLogin courierLogin = StaticInput.staticCourierLogIn();
        loginResponse = Responses.loginCourierResponse(courierLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(LOGIN_SUCCESS_CODE)
                .and()
                .body(LOGIN_SUCCESS_BODY, isA(Integer.class))
                .and()
                .body(LOGIN_SUCCESS_BODY, notNullValue());
        courierId = loginResponse.jsonPath().getInt("id");
    }
    @Test
    @Step("Логинимся, не передавая login")
    public void courierLogInNoLogin(){
        CourierCreate courierCreate = StaticInput.staticCourierCreate();
        Responses.createCourierResponse(courierCreate);
        CourierLogin courierLogin = StaticInput.staticCourierLogInNoLogin();
        loginResponse = Responses.loginCourierResponse(courierLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(LOGIN_BAD_REQUEST_CODE)
                .and()
                .body(LOGIN_BAD_REQUEST_BODY, equalTo(LOGIN_BAD_REQUEST_KEY));


    }

    @Test
    @Step("Логинимся, не передавая password")
    public void courierLogInNoPassword(){
        CourierCreate courierCreate = StaticInput.staticCourierCreate();
        Responses.createCourierResponse(courierCreate);
        CourierLogin courierLogin = StaticInput.staticCourierLogInNoPassword();
        loginResponse = Responses.loginCourierResponse(courierLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(LOGIN_BAD_REQUEST_CODE)
                .and()
                .body(LOGIN_BAD_REQUEST_BODY, equalTo(LOGIN_BAD_REQUEST_KEY));

    }

    @Test
    @Step("Логинимся c некорректным login")
    public void courierLogInWrongLogin(){
        CourierCreate courierCreate = StaticInput.staticCourierCreate();
        Responses.createCourierResponse(courierCreate);
        CourierLogin courierLogin = StaticInput.staticCourierLogInWrongLogin();
        loginResponse = Responses.loginCourierResponse(courierLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(LOGIN_NOT_FOUND_CODE)
                .and()
                .body(LOGIN_NOT_FOUND_BODY, equalTo(LOGIN_NOT_FOUND_KEY));

    }

    @Test
    @Step("Логинимся c некорректным password")
    public void courierLogInWrongPassword(){
        CourierCreate courierCreate = StaticInput.staticCourierCreate();
        Responses.createCourierResponse(courierCreate);
        CourierLogin courierLogin = StaticInput.staticCourierLogInWrongPassword();
        loginResponse = Responses.loginCourierResponse(courierLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(LOGIN_NOT_FOUND_CODE)
                .and()
                .body(LOGIN_NOT_FOUND_BODY, equalTo(LOGIN_NOT_FOUND_KEY));

    }

    @After
    public void tearDown(){
        if (courierId > 0){
            deleteCourier.deleteCourier(courierId);
            System.out.println("Курьер " + courierId +" удален");}
        else{
            System.out.println("Курьер не создан");}
    }}


