package com.learning.testingdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestingDemoApplicationTests {

    @Test
    void additionOf3and5() {
        int ans = 3+5;

        Assertions.assertEquals(8, ans,"Addition of 3 & 5");
    }

}
