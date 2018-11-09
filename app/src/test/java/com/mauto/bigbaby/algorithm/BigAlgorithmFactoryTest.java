package com.mauto.bigbaby.algorithm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by haohuidong on 18-11-9.
 */
public class BigAlgorithmFactoryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void realRandom() throws Exception {
        BigAlgorithmFactory.realRandom(30);
    }

}