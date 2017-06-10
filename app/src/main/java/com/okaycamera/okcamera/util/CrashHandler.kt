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

package com.okaycamera.okcamera.util

import android.content.Context
import android.os.Environment.getExternalStorageDirectory as ESDIR
import android.os.Looper
import android.widget.Toast

class CrashHandler private constructor(): Thread.UncaughtExceptionHandler {

    private val TAG = CrashHandler::class.java.simpleName
    private val APP_CACHE_PATH: String = ESDIR().path + "OkayCamera/crash/"
    private val INSTANCE = CrashHandler()
    private var mContext: Context? = null
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    /**
     * the instance of CrashHandler before use it , you should call init() to initialize it
     */
    fun getInstance(): CrashHandler {
        return INSTANCE
    }

    /**
     * initialize Crash Handler class with Context
     */
    fun init(ctx: Context) {
        mContext = ctx
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (!handleException(e) && mDefaultHandler != null) {
            mDefaultHandler!!.uncaughtException(t, e)
        } else {
            // exception has handled
        }

    }

    private fun handleException(e: Throwable?) : Boolean {
        if (e == null) {
            return false
        }
        if (mContext == null) {
            throw Exception("CrashHandler has not been init")
        }
        sandCrashToServer(mContext, e)
        Thread {
            run {
                Looper.prepare()
                notifyUser()
                Looper.loop()
            }
        }.start()
        saveCrashInfoInFile(e)
        return true
    }

    fun notifyUser() {
        // TODO notify user with dialog window
        Toast.makeText(mContext, "handle exception", Toast.LENGTH_SHORT).show()
    }

    fun sandCrashToServer(context: Context?, e: Throwable){
        // TODO: sand Crash To Server
    }

    fun  saveCrashInfoInFile(ex: Throwable){
        // TODO save crash info in flile
    }
}
