package config;

import base.CourierCreate;
import base.CourierLogin;
import base.Orders;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static config.Constants.*;
import static io.restassured.RestAssured.given;

public class Responses {
    @Step("Посылаем запрос на создание курьера")
    public static Response createCourierResponse(CourierCreate courierCreate){
        return given()
                .header("Content-type", "application/json")
                .body(courierCreate)
                .when()
                .post(CREATE_COURIER);

    }
    @Step("Посылаем запрос логин курьера")
    public static Response loginCourierResponse(CourierLogin courierLogin){
        return  given()
                .header("Content-type", "application/json")
                .body(courierLogin)
                .when()
                .post(LOGIN_COURIER);

    }
    @Step("Посылаем запрос на создание заказа, передаем указание цвета через параметризацию")
    public static Response createGenericOrder(Orders orders){
        return given()
                .header("Content-type", "application/json")
                .body(orders)
                .when()
                .post(CREATE_ORDER);
    }
    @Step("Посылаем запрос на получение списка заказов")
    public static Response getOrdersList(){
        return given()
                .when()
                .get(CREATE_ORDER);
    }
}
