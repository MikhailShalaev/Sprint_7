package utils;

import base.CourierCreate;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
public class RandomInput {
    @Step("Заполняем обязательные поля произвольными данными")
    public static CourierCreate randomCourier(){
        final String login = RandomStringUtils.randomAlphabetic(5);
        final String password = RandomStringUtils.randomAlphanumeric(6);
        final String firstName = RandomStringUtils.randomAlphabetic(7);

        return new CourierCreate(login, password, firstName);
    }
    @Step("Заполняем поля произвольными данными, не заполняем login")
    public static CourierCreate randomCourierNoLogin(){
        final String login = "";
        final String password = RandomStringUtils.randomAlphanumeric(6);
        final String firstName = RandomStringUtils.randomAlphabetic(7);

        return new CourierCreate(login, password, firstName);
    }
    @Step("Заполняем поля произвольными данными, не заполняем password")
    public static CourierCreate randomCourierNoPassword(){
        final String login = RandomStringUtils.randomAlphabetic(5);;
        final String password = "";
        final String firstName = RandomStringUtils.randomAlphabetic(7);

        return new CourierCreate(login, password, firstName);
    }

}
