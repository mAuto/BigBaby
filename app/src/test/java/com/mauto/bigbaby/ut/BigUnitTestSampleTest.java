package com.mauto.bigbaby.ut;


import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by haohuidong on 18-8-24.
 */

public class BigUnitTestSampleTest {

    BigUnitTestSample mSample;

    @Before
    public void setUp() throws Exception {
        mSample = new BigUnitTestSample();
    }

    @Test
    public void appendString() throws Exception {
        String result = mSample.appendString("Hello ", "world");
        Assert.assertNotNull(result);
        Assert.assertEquals(new String("Hello world"), result);
        Assert.assertNotSame(new String("Hello world"), result);
    }

    @After
    public void tearDown() throws Exception {
        // 注销相关的操作
    }

}