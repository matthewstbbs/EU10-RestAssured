package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests_1 {


//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result

    String baseUrl = "http://54.90.80.125:8000"; // ip adresi bize ait olan  olmalı. en azından bu örnekte öyle dursun. diğerlerinde zaman zaman okulun verdikleri var.
    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");      // ---> concatenate yaparak "End Point" ekledik. böylece all spartans'ı çağırdık.

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //priting response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to do API testing then ?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);

        //verify content type is application/json
        Assertions.assertEquals(response.contentType(),"application/json");  // *** "this 60% of the API testing guys."

    }


                            //          yeni örnek,  contains ile assertion yapma
     /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl + "/api/spartans/3");


        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("application/json",response.contentType());

        //verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));    // ** this is NOT a good way. but early ages of learning:)

    }


    //          yeni örnek,  zamanın gösteriminin kontrol edilmesi,
    /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */

    @DisplayName("GET request to /api/hello")
    @Test
    public void test3(){
        //send request and save response inside the response object
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //verify we have headers named date
                 //we use hasHeaderWithname method to verify header exist or not - it returns boolean
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //how to get an header from response using header key ?
                //we use response.header(String headerName) method to get any header value
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());   // burada çalıştı. öünkü API response "Text" olarak veriliyor. response eğer JOSN veya XML olsaydı bu yanlış olurdu.
    }
}
