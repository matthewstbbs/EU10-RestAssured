package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://54.90.80.125:1000/ords/hr";   //eskisi 44.202.119.26

        //get ip address from configuraitons
        String dbUrl = "jdbc:oracle:thin:@54.90.80.125:1521:xe";
        String dbUsername = "hr";  //hr API için
        String dbPassword = "hr";  //hr API için

      //  DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){

        //DBUtils.destroy();
    }
}
