package com.learning;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("App Class Tests")
@TestMethodOrder(MethodOrderer.Random.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AppTest {

    @DisplayName("Addition of 3+2")
    @ParameterizedTest
    @MethodSource("additionparameter")
    void additionDemo(int a, int b, int expectedResult){
        App myapp = new App();
        int result = myapp.additionMethod(a,b);
        assertEquals(expectedResult,result,"Addition of 2 & 3 is 5");
    }

    private static Stream<Arguments> additionparameter(){
        return Stream.of(
                Arguments.of(10,20, 30),
                Arguments.of(10,-20,-10),
                Arguments.of(50,20,70)
        );
    }

    @DisplayName("Addition of -3+10")
    @RepeatedTest(5)
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
