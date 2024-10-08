package config;

public class Constants {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String CREATE_COURIER = "/api/v1/courier";
    public static final String LOGIN_COURIER = "/api/v1/courier/login";
    public static final String CREATE_ORDER = "/api/v1/orders";
    public static final String DELETE_ORDER = "/api/v1/orders/cancel";

    public static final int CREATE_OK_CODE = 201;
    public static final String CREATE_OK_BODY = "ok";
    public static final boolean CREATE_OK_KEY = true;
    public static final int CREATE_BAD_REQUEST_CODE = 400;
    public static final String CREATE_BAD_REQUEST_CONFLICT_BODY = "message";
    public static final String CREATE_BAD_REQUEST_KEY = "Недостаточно данных для создания учетной записи";
    public static final int CREATE_CONFLICT_CODE = 409;
    public static final String CREATE_CONFLICT_KEY = "Этот логин уже используется. Попробуйте другой.";

    public static final int LOGIN_SUCCESS_CODE = 200;
    public static final String LOGIN_SUCCESS_BODY = "id";
    public static final int LOGIN_BAD_REQUEST_CODE = 400;
    public static final String LOGIN_BAD_REQUEST_BODY = "message";
    public static final String LOGIN_BAD_REQUEST_KEY = "Недостаточно данных для входа";
    public static final int LOGIN_NOT_FOUND_CODE = 404;
    public static final String LOGIN_NOT_FOUND_BODY = "message";
    public static final String LOGIN_NOT_FOUND_KEY = "Учетная запись не найдена";

    public static final String STATIC_LOGIN = "Elvis";
    public static final String STATIC_PASSWORD = "66666666";
    public static final String STATIC_FIRSTNAME = "Аристарх";

    public static final int ORDER_CREATE_OK_CODE = 201;
    public static final String ORDER_CREATE_OK_BODY = "track";
    public static final int ORDER_GET_LIST_OK_CODE = 200;
}
