package utils;

import io.qameta.allure.Step;
import static config.Constants.CREATE_COURIER;
import static io.restassured.RestAssured.given;

public class DeleteCourierById {
    @Step("Удаляем учетную запись")
    public void deleteCourier(int id) {
        given().delete(CREATE_COURIER + "/" + id);
    }
}
