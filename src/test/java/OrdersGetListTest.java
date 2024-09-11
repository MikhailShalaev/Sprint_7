import config.Constants;
import config.Responses;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static config.Constants.ORDER_GET_LIST_OK_CODE;
import static org.hamcrest.Matchers.greaterThan;

public class OrdersGetListTest {
    private Response getOrderResponse;
    @Before
    public void setUp(){
        RestAssured.baseURI = Constants.BASE_URL;
    }

    @Test
    @Step("Запрашиваем список заказов")
    public void doesGetOrderReturnsOrderList(){
        getOrderResponse = Responses.getOrdersList();
        getOrderResponse.then()
                .assertThat()
                .statusCode(ORDER_GET_LIST_OK_CODE)
                .and()
                .body("size()", greaterThan(0));
    }
}
