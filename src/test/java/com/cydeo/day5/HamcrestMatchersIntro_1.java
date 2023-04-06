package com.cydeo.day5;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.*; //satır 17'nin başında idi ama static import yapıp bir daha class içinde yazmayacak.
import static org.hamcrest.Matchers.*; //satır 18'de "is" in önündeydi. ama static import yapıp bir daha class içinde yazmayacak.

public class HamcrestMatchersIntro_1 {    // restAssured dependency'nin içinde var. ayrıca birşey eklemeye gerek yok. metodlar için link: https://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html

    @DisplayName("assertThat with numbers")
    @Test
    public void simpleTest1(){

        //actual 5+5
        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));

        //matchers has 2 overloaded version
            //first that accept actual value
            //second that accept another matchers
            //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        //negative
        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));
    }

    @DisplayName("assertThat with String")
    @Test
    public void stringHamcrest(){

        String text = "B22 is learning Hamcrest";

        //checking for euqality is same as numbers
        assertThat(text,is("B22 is learning Hamcrest"));
        assertThat(text,equalTo("B22 is learning Hamcrest"));
        assertThat(text,is(equalTo("B22 is learning Hamcrest")));

        //check if this text starts with B22
        assertThat(text,startsWith("B22"));
        //now do it in case insensitive manner
        assertThat(text,startsWithIgnoringCase("b22"));
        //endswith
        assertThat(text,endsWith("rest"));

        //check if text contains String learning
        assertThat(text,containsString("learning"));
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNING"));

        String str ="  ";

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());
    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers,hasSize(10));
        //check if this list hasItem 77
        assertThat(listOfNumbers,hasItem(77));
        //check if this list hasItems 77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23));    // hasItems-->collections'larda(array veren response) kullanılabilir.

        //check if all numbers greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }


}
