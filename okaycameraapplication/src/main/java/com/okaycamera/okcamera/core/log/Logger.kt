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

package com.okaycamera.okcamera.core.log

import android.util.Log
import com.okaycamera.okcamera.manager.FunManager

/**
 * Usage:
 * Logger.d(TAG, "your debug message");
 */
class Logger {

    companion object {
        internal val whichLogger = FunManager.useWhichLogger()
        val DEFAULT = 0 // android.util.log

        public fun v(tag: String, msg: String) {
            when(whichLogger) {
                DEFAULT -> Log.v(tag,msg)
            }
        }

        public fun v(tag: String, msg: String, t: Throwable) {
            when(whichLogger) {
                DEFAULT -> Log.v(tag,msg,t)
            }
        }

        public fun d(tag: String, msg: String) {
            when(whichLogger) {
                DEFAULT -> Log.d(tag,msg)
            }
        }

        public fun d(tag: String, msg: String, t: Throwable) {
            when(whichLogger) {
                DEFAULT -> Log.d(tag,msg,t)
            }
        }

        public fun i(tag: String, msg: String) {
            when(whichLogger) {
                DEFAULT -> Log.i(tag,msg)
            }
        }

        public fun i(tag: String, msg: String, t: Throwable) {
            when(whichLogger) {
                DEFAULT -> Log.i(tag,msg,t)
            }
        }

        public fun w(tag: String, msg: String) {
            when(whichLogger) {
                DEFAULT -> Log.w(tag,msg)
            }
        }

        public fun w(tag: String, t: Throwable) {
            when(whichLogger) {
                DEFAULT -> Log.w(tag,t)
            }
        }

        public fun w(tag: String, msg: String, t: Throwable) {
            when(whichLogger) {
                DEFAULT -> Log.w(tag,msg,t)
            }
        }

        public fun e(tag: String, msg: String) {
            when(whichLogger) {
                DEFAULT -> Log.e(tag,msg)
            }
        }

        public fun e(tag: String, msg: String, t: Throwable) {
            when(whichLogger) {
                DEFAULT -> Log.e(tag,msg,t)
            }
        }

        public fun wtf(tag: String, msg: String): Int {
            when(whichLogger) {
                DEFAULT -> return Log.wtf(tag,msg)
                else -> return 0
            }
        }

        public fun wtf(tag: String, tr: Throwable): Int {
            when(whichLogger) {
                DEFAULT -> return Log.wtf(tag,tr)
                else -> return 0
            }
        }

        public fun wtf(tag: String, msg: String, tr: Throwable): Int {
            when(whichLogger) {
                DEFAULT -> return Log.wtf(tag,msg,tr)
                else -> return 0
            }
        }

        public fun println(priority: Int, tag: String, msg: String): Int {
            when(whichLogger) {
                DEFAULT -> return Log.println(priority,tag,msg)
                else -> return 0
            }
        }

        public fun isLoggable(tag: String, level: Int): Boolean {
            when(whichLogger) {
                DEFAULT -> return Log.isLoggable(tag,level)
                else -> return false
            }
        }

        public fun getStackTraceString(tr: Throwable): String {
            when(whichLogger) {
                DEFAULT -> return Log.getStackTraceString(tr)
                else -> return ""
            }
        }
    }
}
