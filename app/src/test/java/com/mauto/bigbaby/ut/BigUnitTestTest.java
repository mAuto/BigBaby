package com.mauto.bigbaby.ut;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by haohuidong on 18-8-27.
 */
public class BigUnitTestTest {

//    @InjectMocks
//    private Printer mPrinter;

//    @Mock
    private BigUnitTestSample mSample;

    /**
     * 被这个注解标识过的方法在每个test方法执行之前都会调用一次。
     * */
    @Before
    public void setUp() throws Exception {
        // 用来做一些初始化的操作
        mSample = new BigUnitTestSample();
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAnno(){
        mSample.appendString("Hello ", "What ???");

    }

    /**
     * 需要被执行的测试方法
     * */
    @Test
    public void appendString() throws Exception {
        Printer printer = mock(Printer.class);
        mSample.addPrinter(printer);
//        doReturn("Hello Everyone !!!")
//                .doCallRealMethod()
//                .doAnswer(new Answer() {
//                    @Override
//                    public Object answer(InvocationOnMock invocation) throws Throwable {
//                        Object[] arguments = invocation.getArguments();
//                        String arg_0 = (String) arguments[0];
//
//                        if (TextSys.isEmpty(arg_0)) {
//                            arg_0 = "Err";
//                        }else {
//                            if (arg_0.contains("Mockito")){
//                                arg_0 = arg_0.replace("Mockito", "Big Mockito");
//                            }else if (arg_0.contains("World")) {
//                                arg_0 = arg_0.replace("World", "Small World");
//                            }else {
//                                arg_0 = "What ???";
//                            }
//                        }
//
//                        return arg_0;
//                    }
//                })
//                .doThrow(IllegalArgumentException.class)
//                .when(printer).printMsg(anyString());
//        mSample.appendString("Hello ", "Mockito !!!");
//        mSample.appendString("Hello ", "World !!!");
//        mSample.appendString("Hello ", "What ???");
//        mSample.appendString("Hello ", "Mockito !!!");
//        mSample.appendString("Hello ", "World !!!");
//        mSample.appendString("Hello ", "What ???");
        when(printer.printMsg("Hello")).thenReturn("World");
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
//        doNothing()
//                .doReturn("Hello Everyone !!!")
//                .doThrow(IllegalArgumentException.class)
//                .doCallRealMethod()
//                .doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                Object[] arguments = invocation.getArguments();
//                String arg_0 = (String) arguments[0];
//
//                if (TextSys.isEmpty(arg_0)) {
//                    arg_0 = "Err";
//                }else {
//                    if (arg_0.contains("Mockito")){
//                        arg_0 = arg_0.replace("Mockito", "Big Mockito");
//                    }else if (arg_0.contains("World")) {
//                        arg_0 = arg_0.replace("World", "Small World");
//                    }else {
//                        arg_0 = "What ???";
//                    }
//                }
//
//                return arg_0;
//            }
//        }).when(printer).printMsg(anyString());
    }

    @Test
    public void fun_1() throws Exception {
        // 具体的测试方法
        Printer printer = mock(Printer.class);
        mSample.addPrinter(printer);
        doReturn("msg_0", "msg_1", "msg_2").when(printer).printMsg(anyString());
        mSample.appendString("Hello ", "Mockito !!!");
        mSample.appendString("Hello ", "Mockito !!!");
        mSample.appendString("Hello ", "Mockito !!!");
    }

    @Test
    public void fun_2() throws Exception {
        Printer printer = mock(Printer.class);
        mSample.addPrinter(printer);
        Inputer inputer = mock(Inputer.class);
        mSample.addInputer(inputer);

        mSample.appendString("Hello ", "Mockito !!!");

        InOrder order = inOrder(inputer, printer);
        order.verify(printer).printMsg("Hello ");
        order.verify(inputer).inputMsg("Hello ");
        order.verify(printer).printMsg("Mockito !!!");
        order.verify(inputer).inputMsg("Mockito !!!");
        order.verify(printer).printMsg("Hello Mockito !!!");
        order.verify(inputer).inputMsg("Hello Mockito !!!");
    }

    @Test
    public void testSpy() throws Exception {
        BigUnitTestSample sample = mock(BigUnitTestSample.class);
        BigUnitTestSample spy = spy(sample);
        when(spy.appendString("a", "b")).thenReturn("Hello");
        LogSys.print(spy.appendString("a", "b"));
    }

    @Test
    public void testThen(){
        List<String> mockList = mock(ArrayList.class);
        // 注入mock
        mSample.addMockList(mockList);
        mSample.addMockNetHunter(mock(NetHunter.class));

        int posWanted = 1;
        // 使mock对象执行真实逻辑
        doCallRealMethod().when(mockList).get(posWanted);

//        doReturn("pos: "+posWanted).when(mockList).get(posWanted);
        when(mockList.get(posWanted)).thenReturn("pos: "+posWanted);

        mSample.getDataByPos(posWanted);
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