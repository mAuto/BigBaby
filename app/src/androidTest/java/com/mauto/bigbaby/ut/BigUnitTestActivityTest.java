package com.mauto.bigbaby.ut;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by haohuidong on 18-9-3.
 */
@RunWith(AndroidJUnit4.class)
public class BigUnitTestActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(BigUnitTestActivity.class);

    @Test
    public void test() {

    }

}