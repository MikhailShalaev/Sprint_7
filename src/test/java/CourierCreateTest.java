import base.CourierCreate;
import base.CourierLogin;
import config.Constants;
import config.Responses;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import utils.DeleteCourierById;
import utils.RandomInput;
import utils.StaticInput;

import static config.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CourierCreateTest {
    private int courierId;
    private CourierLogin courierLogin;
    DeleteCourierById deleteCourier = new DeleteCourierById();
    @Before
    public void setUp(){
        RestAssured.baseURI= Constants.BASE_URL;
    }

    @Test
    @Step("Создаем  курьера со всеми обязательными полями")
    public void courierCanBeCreatedPositive(){
        CourierCreate courierCreate = RandomInput.randomCourier();
        Response response = Responses.createCourierResponse(courierCreate);
        response.then()
                .assertThat()
                .body(CREATE_OK_BODY, equalTo(CREATE_OK_KEY))
                .and()
                .statusCode(CREATE_OK_CODE);
        System.out.println(response.getBody().asString());
        courierId=Responses.loginCourierResponse(CourierLogin.from(courierCreate)).path("id");
    }

    @Test
    @Step("Создаем  курьера, передавая пустой login")
    public void courierCanNotBeCreatedNoLogin(){
        CourierCreate courierCreate = RandomInput.randomCourierNoLogin();
        Response response = Responses.createCourierResponse(courierCreate);
        response.then()
                .assertThat()
                .body(CREATE_BAD_REQUEST_CONFLICT_BODY, equalTo(CREATE_BAD_REQUEST_KEY))
                .and()
                .statusCode(CREATE_BAD_REQUEST_CODE);
        System.out.println(response.getBody().asString());

    }

    @Test
    @Step("Создаем  курьера, передавая пустой password.")
    public void courierCanNotBeCreatedNoPassword(){
        CourierCreate courierCreate = RandomInput.randomCourierNoPassword();
        Response response = Responses.createCourierResponse(courierCreate);
        response.then()
                .assertThat()
                .body(CREATE_BAD_REQUEST_CONFLICT_BODY, equalTo(CREATE_BAD_REQUEST_KEY))
                .and()
                .statusCode(CREATE_BAD_REQUEST_CODE);
        System.out.println(response.getBody().asString());

    }

    @Test
    @Step("Создаем  двух курьеров с одинаковыми регистрационными данными")
    public void canNotCreateIdenticalCouriers(){
        CourierCreate firstStaticCourier = StaticInput.staticCourierCreate();
        CourierCreate secondStaticCourier = StaticInput.staticCourierCreate();
        //Дефект - текст сообщения не соответствует постановке
        Response response = Responses.createCourierResponse(secondStaticCourier);
        response.then()
                .assertThat()
                .body(CREATE_BAD_REQUEST_CONFLICT_BODY, equalTo(CREATE_CONFLICT_KEY))
                .and()
                .statusCode(CREATE_CONFLICT_CODE);
        System.out.println(response.getBody().asString());
        courierId=Responses.loginCourierResponse(CourierLogin.from(firstStaticCourier)).path("id");
    }
    @After
    public void tearDown(){
        if (courierId > 0){
            deleteCourier.deleteCourier(courierId);
            System.out.println("Курьер " + courierId +" удален");}
        else{
            System.out.println("Курьер не создан");}
    }

}
