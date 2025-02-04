package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath_3 extends SpartanTestBase { //BeforeAll 'ı utilities altında SpartanTestBase içine koydu. ve bu class'ı extends olarak tanımladı.yukarıda restAssured ve assertions class için Static yapmayı unutma.



    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    //            ************  JSON .path() Method ile payload values manipule etmek   <TEK SPARTAN için>  ****  !!!!!!!
    @DisplayName("GET one spartan with Path Method")
    @Test
    public void test1(){
    Response response = given().accept(ContentType.JSON)
                            .and().pathParam("id",10)
                    .when().get("/api/spartans/{id}");

    assertEquals(200,response.statusCode());
    assertEquals("application/json",response.contentType());

    //verify each json key has specific value
        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");   // normalde complain etmesi lazım ama .path() dönüşü "T" olan bir şey sayesinde autocasting gerçekleşiyor.
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone"); // normalde complain etmesi lazım ama .path() dönüşü "T" olan bir şey sayesinde autocasting gerçekleşiyor.

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert the values
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);


    }


    //                                        ***   navigate with JSON Path()    <All spartans> WIH Index#      ******
    @DisplayName("GET all spartan and navigate with Path()")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();

        int firstId = response.path("id[0]");               // index numbers
        System.out.println("firstId = " + firstId);

        String name = response.path("name[0]");               // index number
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");    // index number'ın sondan başlamasına örnek
        System.out.println("lastFirstName = " + lastFirstName);

        //save names inside the list of string
        List<String> names = response.path("name");
        System.out.println(names);
        //print each name one by one
        for (String n : names) {
            System.out.println(n);
        }
    }
}
