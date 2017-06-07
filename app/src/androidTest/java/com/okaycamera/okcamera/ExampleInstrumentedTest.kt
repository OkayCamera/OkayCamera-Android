package com.okaycamera.okcamera

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.view.WindowManager
import org.junit.Before
import android.support.test.rule.ActivityTestRule
import org.junit.Rule

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.okaycamera.okcamera", appContext.packageName)
    }

    @get:Rule
    public var mActivityRule = ActivityTestRule<Main>(Main::class.java)

    @Before
    fun setUp() {
        val activity = mActivityRule.getActivity()
        val wakeUpDevice = Runnable {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
        activity.runOnUiThread(wakeUpDevice)
    }
}
