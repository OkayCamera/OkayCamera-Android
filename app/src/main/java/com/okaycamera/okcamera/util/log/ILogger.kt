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

internal interface ILogger {

    fun v(tag: String, msg: String)
    fun v(tag: String, msg: String, t: Throwable)
    fun d(tag: String, msg: String)
    fun d(tag: String, msg: String, t: Throwable)
    fun i(tag: String, msg: String)
    fun i(tag: String, msg: String, t: Throwable)
    fun w(tag: String, msg: String)
    fun w(tag: String, t: Throwable)
    fun w(tag: String, msg: String, t: Throwable)
    fun e(tag: String, msg: String)
    fun e(tag: String, msg: String, t: Throwable)

    fun wtf(tag: String, msg: String): Int
    fun wtf(tag: String, tr: Throwable): Int
    fun wtf(tag: String, msg: String, tr: Throwable): Int

    fun println(priority: Int, tag: String, msg: String): Int

    fun isLoggable(tag: String, level: Int): Boolean
    fun getStackTraceString(tr: Throwable): String

    companion object {
        val VERBOSE = 2
        val DEBUG = 3
        val INFO = 4
        val WARN = 5
        val ERROR = 6
        val ASSERT = 7
    }
}
