package cn.vincent.controller;

import org.junit.jupiter.api.Test;

class TestControllerTest {
    TestController testController = new TestController();

    @Test
    void test1() {
        long n = 3;
        assert testController.test1(n) == 6;
    }
}