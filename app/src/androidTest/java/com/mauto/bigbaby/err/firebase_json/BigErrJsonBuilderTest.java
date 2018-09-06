package com.mauto.bigbaby.err.firebase_json;

import com.mauto.bigbaby.ut.LogSys;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by haohuidong on 18-9-6.
 */
public class BigErrJsonBuilderTest {

    BigErrJsonBuilder parser;

    @Before
    public void setUp() throws Exception {
        parser = new BigErrJsonBuilder();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parse() throws Exception {
        String value = parser.parse("title");
        LogSys.print(value);
    }

}