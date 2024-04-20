package Homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Hw6 {
    /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
    @Test
    public void assertionMethod() {

//          https://reqres.in/api/unknown/3

//            User send a GET request to the URL
        Response response = RestAssured.get("https://reqres.in/api/unknown/3");//get() method will return Response
        response.prettyPrint();

//            HTTP Status Code should be 200
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

//            Response content type is "application/json; charset=utf-8"
        response.then().contentType("application/json; charset=utf-8");

//            Response body should be like;(Soft Assertion)
//        {
//        "data": {
//            "id": 3,
//            "name": "true red",
//            "year": 2002,
//            "color": "#BF1932",
//            "pantone_value": "19-1664"
//        },
        response
                .then()
                .body("data.id",equalTo(3))
                .body("data.name",equalTo("true red"))
                .body("data.year",equalTo(2002))
                .body("data.color",equalTo("#BF1932"))
                .body("data.pantone_value",equalTo("19-1664"))

                //        "support": {
//            "url": "https://reqres.in/#support-heading",
//            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//        }
//}
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

    }
}
