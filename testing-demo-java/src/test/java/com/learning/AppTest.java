package com.learning;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

@DisplayName("App Class Tests")
class AppTest {

    @Test
    @DisplayName("Addition of 3+2")
    void additionDemo2and3(){
        App myapp = new App();
        int result = myapp.additionMethod(2,3);
        assertEquals(5,result,"Addition of 2 & 3 is 5");
    }

    @Test
    @DisplayName("Addition of -3+10")
    @Disabled
    void additionOf_Negative3_Positive10(){
        App myApp = new App();
        int result = myApp.additionMethod(-3, 10);
        assertEquals(7, result, "Addition of -3 & 10 is 7");
        assertNotEquals(8, result, "Addition of -3 & 10 is not 8");
        assertNotNull(myApp,"App class object is not null");
    }

    @BeforeEach
    void startTest(){
        System.out.println("Test Starting");
    }

    @AfterAll
    static void endTest(){
        System.out.println("Tests End");
    }

    @Test
    public void validateException(){
        App app = new App();

        assertThrows(ArithmeticException.class, app::throwException, "Arithmetic Exception Thrown");

    }

}
