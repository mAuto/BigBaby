package com.mauto.bigbaby.ut;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    }

    /**
     * 需要被执行的测试方法
     * */
    @Test
    public void appendString() throws Exception {
        // 具体的测试方法
        BigUnitTestSample sample = Mockito.mock(BigUnitTestSample.class);
        Mockito.verify(sample, times(0)).appendString("0", "0");
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] arguments = invocation.getArguments();
                String tmp = (String) arguments[1];
                if (tmp.equals("answer"))
                    return "World";
                return tmp;
            }
        }).when(sample).appendString(anyString(), any(String.class));

        String result = sample.appendString("Hello", "answer");
        LogSys.print(result);
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