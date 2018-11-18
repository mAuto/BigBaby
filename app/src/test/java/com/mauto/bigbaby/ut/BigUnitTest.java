package com.mauto.bigbaby.ut;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;

/**
 * Created by haohuidong on 18-9-7.
 */
public class BigUnitTest {

    @InjectMocks
    BigUnitTestSample mSample;

    @Mock
    Printer mPrinter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void appendString() throws Exception {
//        doCallRealMethod().when(mSample).appendString(anyString(), anyString());
        doCallRealMethod().when(mPrinter).printMsg(anyString());
        Assert.assertNotNull(mSample.appendString("Hello ", "Mockito !!!"));
    }

}