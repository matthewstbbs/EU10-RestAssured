package com.cydeo.day5;

import com.cydeo.utilities.*;
import io.restassured.response.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest_5 extends SpartanTestBase {


    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                            .when().get("/api/spartans/{id}")
                            .then().statusCode(200).extract().response();


        //get the json and convert it to the map

        Map<String,Object> jsonMap = response.as(Map.class);   // *** burada ilk başta hata verdi. Pom.xml'e Jackson veya GSON dependency ekleyerek library lerini yüklenmeli.

        System.out.println(jsonMap.toString());

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");   // soldaki Object idi String'e casting yaptık.
        assertThat(actualName,is("Meta"));
    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){   //video:3:00

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

                //we need to convert json to java  which is deserialization
        List<Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

        Map<String,Object> spartan3 = jsonList.get(2);   //simple Java
        System.out.println(spartan3);
    }
}
