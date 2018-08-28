package com.mauto.bigbaby.ut;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * Created by haohuidong on 18-8-27.
 */
public class BigUnitTestTest {

    /**
     * 被这个注解标识过的方法在每个test方法执行之前都会调用一次。
     * */
    @Before
    public void setUp() throws Exception {
        // 用来做一些初始化的操作
        Mockito.mock(BigUnitTestSample.class);
    }

    /**
     * 需要被执行的测试方法
     * */
    @Test
    public void appendString() throws Exception {
        // 具体的测试方法
    }

    @Test
    public void fun_0() throws Exception {
        // 具体的测试方法
    }

    @Test
    public void fun_1() throws Exception {
        // 具体的测试方法
    }

    @Test
    public void fun_2() throws Exception {
        // 具体的测试方法
    }

    @After
    /**
     * 被这个注解标识过的方法在每个test方法执行之后都会调用一次。
     * */
    public void tearDown() throws Exception {
        // 用来进行注销操作
    }

    @BeforeClass
    public static void init() {

    }

    @AfterClass
    public static void dispose() {

    }
}