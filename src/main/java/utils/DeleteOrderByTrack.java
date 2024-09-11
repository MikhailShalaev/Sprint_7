package utils;

import io.qameta.allure.Step;
import static config.Constants.DELETE_ORDER;
import static io.restassured.RestAssured.given;

public class DeleteOrderByTrack {
    @Step("Удаляем заказ")
    public void deleteOrder(int track){
        given().put(DELETE_ORDER);
    }
}
