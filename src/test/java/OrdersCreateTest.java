import base.Orders;
import config.Constants;
import config.Responses;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import utils.DeleteOrderByTrack;
import utils.StaticInput;

import static config.Constants.*;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class OrdersCreateTest {
    private int orderTrack;
    private DeleteOrderByTrack deleteOrder = new DeleteOrderByTrack();
    private Orders orders;
    private Response createOrderResponse;
    private String[] color;

    public OrdersCreateTest(String[] color) {
        this.color = color;
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = Constants.BASE_URL;
        orders = StaticInput.orderDataSetNoColor();
    }

    @Parameters
    public static Object[][] colors() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{}},
        };
    }

    @Test
    @Step("Создаем заказ")
    public void createOrderWithDifferentColors() {
        orders.setColor(color);
        createOrderResponse = Responses.createGenericOrder(orders);
        createOrderResponse.then()
                .assertThat()
                .statusCode(ORDER_CREATE_OK_CODE)
                .and()
                .body(ORDER_CREATE_OK_BODY, isA(Integer.class))
                .and()
                .body(ORDER_CREATE_OK_BODY, notNullValue());
        orderTrack = createOrderResponse.jsonPath().getInt("track");
    }
    @After
    public void tearDown(){
        if (orderTrack > 0){
            deleteOrder.deleteOrder(orderTrack);
            System.out.println("Заказ " + orderTrack +" удален");}
        else{
            System.out.println("Заказ не создан");}
    }
}