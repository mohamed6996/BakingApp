package com.tastey.baking.bakingapp;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tastey.baking.bakingapp.activities.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppTest {

    private IdlingResource idlingResource;


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingRes() {
        MyApp myApp = (MyApp) mActivityTestRule.getActivity().getApplicationContext();
        idlingResource = myApp.getIdlingRes();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void unregisterIdlingRes() {
        IdlingRegistry.getInstance().unregister(idlingResource);

    }

    @Test
    public void moveFromMainFrag_ToDetailFrag() {
        ViewInteraction mainRecyclerView = onView(
                allOf(withId(R.id.main_rec_view),
                        childAtPosition(
                                withId(R.id.main_frag),
                                0)));

        mainRecyclerView.perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.quantityAndMeasure), withText("( 2.0 "), isDisplayed()));
        onView(allOf(withId(R.id.quantityAndMeasure), withText("( 6.0 "), isDisplayed()));
    }


    @Test
    public void checkExoPlayerVisibility() {

        ViewInteraction mainRecyclerView = onView(
                allOf(withId(R.id.main_rec_view),
                        childAtPosition(
                                withId(R.id.main_frag),
                                0)));

        mainRecyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction stepsRecyclerView = onView(
                allOf(withId(R.id.step_rec_view),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        stepsRecyclerView.perform(actionOnItemAtPosition(0, click()));


        onView(allOf(withId(R.id.exo_play), withContentDescription("Play"), isDisplayed()));
    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) { // see error msg here if something doesn`t match
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }


}
