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

package com.example.eventbusdemo;

import android.util.Log;

public class ProguardTest {
    private static final String TAG = "ProguardTest";
    // 通过打印log证明此类和方法是否已经被混淆，确认是否混淆成功，也可通过发编译确认。
    public void proguardTest() {
        Log.d(TAG, "proguardTest: ", new Exception());
    }
}
