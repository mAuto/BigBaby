package com.mauto.bigbaby.ut;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by haohuidong on 18-8-27.
 */
public class BigUnitTestTest {

    private BigUnitTestSample mSample;

    /**
     * 被这个注解标识过的方法在每个test方法执行之前都会调用一次。
     * */
    @Before
    public void setUp() throws Exception {
        // 用来做一些初始化的操作
        mSample = new BigUnitTestSample();
    }

    /**
     * 需要被执行的测试方法
     * */
    @Test
    public void appendString() throws Exception {
        // 具体的测试方法
        Printer printer = mock(Printer.class);
        mSample.addPrinter(printer);
        doCallRealMethod().when(printer).print(anyString());
        mSample.appendString("Hello ", "Mockito !!!");
//        verify(printer).print("Hello Mockito !!!");// verify(printer, times(1)).print("Hello Mockito !!!");
//        verify(printer).print("Hello World !!!");
    }

    @Test
    public void fun_0() throws Exception {
        // 具体的测试方法
        BigUnitTestSample sample = Mockito.mock(BigUnitTestSample.class);
//        Mockito.verify(sample, times(0)).appendString("0", "0");
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                Object[] arguments = invocation.getArguments();
//                String arg0 = (String) arguments[0];
//                String tmp = (String) arguments[1];
//                if (tmp.equals("answer"))
//                    tmp = "world";
//                return arg0 + tmp;
//            }
//        }).doReturn("test").when(sample).appendString(anyString(), any(String.class));
//
//
//        String result = sample.appendString("Hello ", "answer");
//        LogSys.print(result);
//        result = sample.appendString("Hello ", "answer");
//        LogSys.print(result);
//        Mockito.verify(sample, times(0)).appendString("Hello ", "answer");
        doNothing().when(sample).appendString("Hello ", "Mockito !!!");
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

    @Test
    public void appendStringWithoutReturn() {
        BigUnitTestSample sample = mock(BigUnitTestSample.class);
        doNothing().when(sample).appendStringWithoutReturn("Hello ", "Mockito !!!");
    }
}