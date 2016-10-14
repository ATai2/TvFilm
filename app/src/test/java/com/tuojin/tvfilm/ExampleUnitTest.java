package com.tuojin.tvfilm;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Date date=new Date();
        long time = date.getTime();
        System.out.println(time);
        assertEquals(4, 2 + 2);
    }
}