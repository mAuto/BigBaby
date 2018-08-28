package com.mauto.bigbaby.ut;


import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.collection.IsArrayWithSize.arrayWithSize;
import static org.hamcrest.core.IsNot.not;

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
        String[] tmps = new String[5];
        Assert.assertThat(tmps, arrayWithSize(lessThan(5)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void equals() throws Exception {
        mSample.equals(new BigUnitTestSample());
    }

    @After
    public void tearDown() throws Exception {
        // 注销相关的操作
    }

}