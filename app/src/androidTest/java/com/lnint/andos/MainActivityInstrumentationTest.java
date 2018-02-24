package com.lnint.andos;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lnint.example.behavior.ScrollingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Espresso UI测试--在Android设备或模拟器上执行单元测试
 * Created by wangpf on 2018/2/24.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {
    private static final String STRING_TO_BE_TYPED = "Hello World!";

    @Rule
    public ActivityTestRule<ScrollingActivity> mActivityRule = new ActivityTestRule<>(ScrollingActivity.class);

    @Test
    public void sayHello() {
        //onView(withId(R.id.txt_hello)).perform(typeText(STRING_TO_BE_TYPED)); //line 1
        //onView(withId(R.id.txt_hello)).check(matches(withText(STRING_TO_BE_TYPED)));
        //onView(withId(R.id.btn_picker)).perform(click()); //line 2
    }
}
