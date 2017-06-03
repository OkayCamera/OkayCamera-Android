/*
 * Copyright (c) 2017, Jianguo Yang.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.okaycamera.okcamera

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.okaycamera.okcamera.manager.FunManager
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // define lunch animation, must call before onCreate()
        doLunchAnim(true)
        super.onCreate(savedInstanceState)
        // full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                             WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        initView();
    }

    /**
     * init this Activity's views
     */
    fun initView() {
        sample_text.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun finish() {
        super.finish()
        doLunchAnim(false)
    }

    /**
     * the activity enter or close animatio
     *  @param isStart true if activity start, false if activity close
     */
    fun doLunchAnim(isStart: Boolean) {
        // fixme: click home quit application the animation will not start
        if (FunManager().useLunchAnim()){
            if (isStart) {
                overridePendingTransition(R.anim.anim_app_open_enter, R.anim.anim_app_open_exit)
            } else {
                overridePendingTransition(R.anim.anim_app_close_enter, R.anim.anim_app_close_exit)
            }
        } else {
            overridePendingTransition(0, 0)
        }
    }
}
