package com.mauto.bigbaby.ut;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mauto.bigbaby.R;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftOf;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


/**
 * Created by haohuidong on 18-9-3.
 */
@RunWith(AndroidJUnit4.class)
public class BigUnitTestActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(BigUnitTestActivity.class);

    private BigIdlingResource mIdlingResource;
    @Before
    public void setup(){
        mIdlingResource = new BigIdlingResource((BigUnitTestActivity) rule.getActivity());
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(mIdlingResource);
    }

    @Test
    public void test() {
        onView(withId(R.id.btn_fill_data))
                .perform(click());

        onData(allOf(is(instanceOf(String.class)), is("FRIENDS")))
                .inAdapterView(withId(R.id.lv_sample))
//                .atPosition(0) // ListView中position=0的item
//                .inRoot(RootMatchers.isDialog()) // dialog的视图中的AdapterView
//                .onChildView(withText("No Lie")) // ListView的item中text为“No Lie”的view
                .perform(click());

        onView(withId(R.id.tv_content)).check(matches(withText("LV -> FRIENDS")));

//        onView(withId(R.id.rv_sample))
//                .perform(RecyclerViewActions.actionOnItemAtPosition().actionOnItemAtPosition(10, longClick()));
    }

}