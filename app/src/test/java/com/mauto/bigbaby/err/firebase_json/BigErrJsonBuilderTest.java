package com.mauto.bigbaby.err.firebase_json;

import com.mauto.bigbaby.ut.LogSys;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by haohuidong on 18-9-6.
 */
public class BigErrJsonBuilderTest {

    @InjectMocks
    JSONObject parent;

    @Mock
    BigErrJsonBuilder mParser;

    @Before
    public void setUp() throws Exception {
//        mParser = new BigErrJsonBuilder();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parse() throws Exception {
        String json = "{title:homesick, singer:Dua lipa, ablum:Homesick}";
        String value = null;
        try {
            JSONObject parent = new JSONObject(json);
            value = parent.getString("title");
        } catch (JSONException e) {
            value = e.toString();
        }

        LogSys.print(value);
    }

}