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

package com.okaycamera.okcamera.util.log

import android.util.Log
import com.okaycamera.okcamera.manager.FunManager

class Logger : ILogger {

    internal val whichLogger = FunManager.useWhichLogger()
    val DEFAULT = 0


    override fun v(tag: String, msg: String) {
        when(whichLogger) {
            DEFAULT -> Log.v(tag,msg)
        }
    }

    override fun v(tag: String, msg: String, t: Throwable) {
        when(whichLogger) {
            DEFAULT -> Log.v(tag,msg,t)
        }
    }

    override fun d(tag: String, msg: String) {
        when(whichLogger) {
            DEFAULT -> Log.d(tag,msg)
        }
    }

    override fun d(tag: String, msg: String, t: Throwable) {
        when(whichLogger) {
            DEFAULT -> Log.d(tag,msg,t)
        }
    }

    override fun i(tag: String, msg: String) {
        when(whichLogger) {
            DEFAULT -> Log.i(tag,msg)
        }
    }

    override fun i(tag: String, msg: String, t: Throwable) {
        when(whichLogger) {
            DEFAULT -> Log.i(tag,msg,t)
        }
    }

    override fun w(tag: String, msg: String) {
        when(whichLogger) {
            DEFAULT -> Log.w(tag,msg)
        }
    }

    override fun w(tag: String, t: Throwable) {
        when(whichLogger) {
            DEFAULT -> Log.w(tag,t)
        }
    }

    override fun w(tag: String, msg: String, t: Throwable) {
        when(whichLogger) {
            DEFAULT -> Log.w(tag,msg,t)
        }
    }

    override fun e(tag: String, msg: String) {
        when(whichLogger) {
            DEFAULT -> Log.e(tag,msg)
        }
    }

    override fun e(tag: String, msg: String, t: Throwable) {
        when(whichLogger) {
            DEFAULT -> Log.e(tag,msg,t)
        }
    }

    override fun wtf(tag: String, msg: String): Int {
        when(whichLogger) {
            DEFAULT -> return Log.wtf(tag,msg)
            else -> return 0
        }
    }

    override fun wtf(tag: String, tr: Throwable): Int {
        when(whichLogger) {
            DEFAULT -> return Log.wtf(tag,tr)
            else -> return 0
        }
    }

    override fun wtf(tag: String, msg: String, tr: Throwable): Int {
        when(whichLogger) {
            DEFAULT -> return Log.wtf(tag,msg,tr)
            else -> return 0
        }
    }

    override fun println(priority: Int, tag: String, msg: String): Int {
        when(whichLogger) {
            DEFAULT -> return Log.println(priority,tag,msg)
            else -> return 0
        }
    }

    override fun isLoggable(tag: String, level: Int): Boolean {
        when(whichLogger) {
            DEFAULT -> return Log.isLoggable(tag,level)
            else -> return false
        }
    }

    override fun getStackTraceString(tr: Throwable): String {
        when(whichLogger) {
            DEFAULT -> return Log.getStackTraceString(tr)
            else -> return ""
        }
    }
}
