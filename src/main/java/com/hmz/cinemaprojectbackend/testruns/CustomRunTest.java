package com.hmz.cinemaprojectbackend.testruns;

import com.hmz.operations.Random;
import com.hmz.test.Tester;

public class CustomRunTest extends Tester {

    public CustomRunTest(int testNumber) {
        super(testNumber);
    }

    void test1() {
        Random random = new Random(3, 10);
        System.out.println(random.getIntRandom());
    }

    public static void main(String[] args) {
        new CustomRunTest(1);
    }
}
