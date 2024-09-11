package utils;

import base.CourierCreate;
import base.CourierLogin;
import base.Orders;
import static config.Constants.*;

public class StaticInput {
    public static CourierCreate staticCourierCreate(){
        final String login = STATIC_LOGIN;
        final String password = STATIC_PASSWORD;
        final String firstName = STATIC_FIRSTNAME;

        return new CourierCreate(login, password, firstName);
    }

    public static CourierLogin staticCourierLogIn() {
        final String login = STATIC_LOGIN;
        final String password = STATIC_PASSWORD;

        return new CourierLogin(login, password);
    }
    public static CourierLogin staticCourierLogInNoLogin(){
        final String login = "";
        final String password = STATIC_PASSWORD;

        return new CourierLogin(login, password);
    }

    public static CourierLogin staticCourierLogInNoPassword(){
        final String login = STATIC_LOGIN;
        final String password = "";

        return new CourierLogin(login, password);
    }
    public static CourierLogin staticCourierLogInWrongLogin(){
        final String login = "Аааааааааа";
        final String password = STATIC_PASSWORD;

        return new CourierLogin(login, password);
    }

    public static CourierLogin staticCourierLogInWrongPassword(){
        final String login = STATIC_LOGIN;
        final String password = "Аааааааааа";

        return new CourierLogin(login, password);
    }

    public static Orders orderDataSetNoColor(){
        return new Orders("Первый",
                "Второй",
                "ул. Мясницкая, 5",
                "Лубянка",
                "+84952741001",
                2,
                "10.10.2024",
                "Быстро"
        );
    }
}
