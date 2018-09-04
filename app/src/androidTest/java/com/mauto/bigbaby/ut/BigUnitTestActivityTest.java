package com.mauto.bigbaby.ut;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mauto.bigbaby.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;


/**
 * Created by haohuidong on 18-9-3.
 */
@RunWith(AndroidJUnit4.class)
public class BigUnitTestActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(BigUnitTestActivity.class);

    @Test
    public void test() {
        onView(withId(R.id.btn_fill_data))
                .check(ViewAssertions.matches(withText("Fill data")));
    }

}