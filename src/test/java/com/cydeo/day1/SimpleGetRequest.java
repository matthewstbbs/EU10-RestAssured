package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.90.80.125:8000/api/spartans";    // ip adresi bize ait olan  olmalı. en azından bu örnekte öyle dursun. diğerlerinde zaman zaman okulun verdikleri var.

    @Test
    public void test1(){
        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //print response status code
        System.out.println(response.statusCode());

        //print response body
        response.prettyPrint();

    }





}
