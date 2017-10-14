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

package com.okaycamera.okcamera.core.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment.*
import android.os.Looper
import android.widget.Toast
import com.okaycamera.okcamera.core.log.Logger
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*
import android.os.Environment.getExternalStorageDirectory as ESDIR


class CrashHandler private constructor(): Thread.UncaughtExceptionHandler {

    private val TAG = CrashHandler::class.java.simpleName
    private val APP_CACHE_PATH: String = ESDIR().path + "OkayCamera/crash/"
    private var mContext: Context? = null
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    private val infos = HashMap<String, String>() //用来存储设备信息和异常信息
    private val formatter = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",Locale.ENGLISH)

    companion object {
        // do not worry, there is application context
        @SuppressLint("StaticFieldLeak")
        private val INSTANCE = CrashHandler()
        /**
         * the instance of CrashHandler before use it , you should call init() to initialize it
         */
        fun getInstance(): CrashHandler {
            return INSTANCE
        }
    }

    /**
     * initialize Crash Handler class with Context
     */
    fun init(ctx: Context) {
        mContext = ctx
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * when UncaughtException happened, call this method to process
     */
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (!handleException(e) && mDefaultHandler != null) {
            // if user has not handle the exception give system to process
            mDefaultHandler!!.uncaughtException(t, e)
        } else {
            // exception has handled
            try {
                Thread.sleep(3000)
            } catch (e : InterruptedException) {
                Logger.e(TAG, "error has handled: " + e)
            }
            // exit application
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }
    }

    /**
     * process the exception, such tell the user, collection devices info, send the exception to server etc.
     */
    private fun handleException(e: Throwable?) : Boolean {
        if (e == null) {
            return false
        }
        if (mContext == null) {
            throw Exception("CrashHandler has not been init")
        }
        Thread {
            run {
                Looper.prepare()
                notifyUser()
                Looper.loop()
            }
        }.start()
        //收集设备参数信息
        collectDeviceInfo(mContext!!)
        // 保存到文件
        saveCrashInfoInFile(e)
        // 上传到服务器
        sandCrashToServer(mContext, e)
        return true
    }

    private fun collectDeviceInfo(mContext: Context) {
        try {
            val pm = mContext.packageManager
            val pi = pm.getPackageInfo(mContext.packageName, PackageManager.GET_ACTIVITIES)
            infos.put("versionName", pi.versionName)
            infos.put("versionCode", pi.versionCode.toString())
        }catch (e : PackageManager.NameNotFoundException){
            Logger.e(TAG, "an error occured when collect package info", e)
        }
        val fields = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                infos.put(field.name, field.get(null).toString())
                Logger.d(TAG, field.name + " : " + field.get(null))
            } catch (e: Exception) {
                Logger.e(TAG, "an error occured when collect crash info", e)
            }
        }
    }

    private fun notifyUser() {
        // TODO notify user with dialog window, use R.String replace
        Toast.makeText(mContext, "an error occured", Toast.LENGTH_SHORT).show()
    }

    private fun sandCrashToServer(context: Context?, e: Throwable){
        // TODO: sand Crash To Server
        // 1. permission request
        // 2. upload
    }

    /**
     * save the crash info to file which in OkayCamera/crash/
     */
    private fun  saveCrashInfoInFile(ex: Throwable) : String?{
        // TODO permission check
        // get key & values about system info
        val sb = StringBuffer()
        for ((key, value) in infos) {
            sb.append(key + "=" + value + "\n")
        }
        sb.append("===============================================================================")
        // get stack Trace
        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        sb.append(writer.toString())

        // write in file
        try {
            val timestamp = System.currentTimeMillis()
            val time = formatter.format(Date())
            val fileName = "crash-$time-$timestamp.log"
            if (getExternalStorageState().equals(MEDIA_MOUNTED)) {
                val dir = File(APP_CACHE_PATH)
                if (!dir.exists()) {
                    dir.mkdirs()
                }
                val fos = FileOutputStream(APP_CACHE_PATH + fileName)
                // 在kotlin中无法通过getByte()方法转换成数组
                fos.write(sb.toString().toByteArray())
                fos.close()
            }
            return fileName
        }catch (e : Exception){
            Logger.e(TAG,"an error occured while writing file...", e)
        }
        return null
    }
}
