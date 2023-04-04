package com.cydeo.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests_2 {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with STATIC method name
    @BeforeAll    // one time before every test. similiar to testNG before
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.90.80.125:1000/ords/hr";   //eskisi baseURI = "http://3.216.30.92:8000";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){

        Response response = get("/regions");   // BeforeAll'a eklenenden sonra buraya sadece end point yazarak rahatlama sağlarız.

        //print the status code
        System.out.println(response.statusCode());

    }

     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)    // RestAssured sınıfını yukarıya static olarak import edince artık her seferinde çağırmak zorunda kalmadan ona ait methodları kullanmaya başladık.
                            .when()
                                    .get("/regions/2");

        //verify status code
        assertEquals(200,response.statusCode()); //Assertion sınıfını yukarıya static olarak import edince artık her seferinde çağırmak zorunda kalmadan ona ait methodları kullanmaya başladık.
        //verify content type
        assertEquals("application/json",response.contentType());

        response.prettyPrint();

        //verify body contains Americas
        assertTrue(response.body().asString().contains("Americas"));

    }





}
