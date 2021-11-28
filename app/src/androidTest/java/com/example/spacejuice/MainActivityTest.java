package com.example.spacejuice;

import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.widget.EditText;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.spacejuice.activity.LoginActivity;
import com.example.spacejuice.activity.WelcomeActivity;
import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);


    @Before
    public void setUp(){
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
        Activity activity = rule.getActivity();
        solo.clickOnButton("Log in");
        assertTrue(solo.waitForActivity(LoginActivity.class));
        solo.enterText((EditText) solo.getView(R.id.userName), "Josh4");
        solo.enterText((EditText) solo.getView(R.id.editTextTextPassword), "12345");
        solo.clickOnButton("Login");
    }

    @Test
    public void start(){


    }

}