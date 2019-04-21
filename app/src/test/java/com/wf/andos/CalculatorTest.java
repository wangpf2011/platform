package com.wf.andos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JVM单元测试--在本机执行单元测试
 * Created by wangpf on 2018/2/24.
 */
public class CalculatorTest {
    private Calculator mCalculator;
    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sum() throws Exception {
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(2d, mCalculator.substract(5d, 4d), 0);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(3d, mCalculator.divide(20d, 5d), 1);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(12d, mCalculator.multiply(2d, 5d), 1);
    }
}