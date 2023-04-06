package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.90.80.125:8000";   // eskisi 3.216.30.92

        String dbUrl = "jdbc:oracle:thin:@54.90.80.125:1521:xe";   // eskisi 3.216.30.92
        String dbUsername = "SP";   //spartan API için
        String dbPassword = "SP";   //spartan API için

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){

        DBUtils.destroy();
    }



}
