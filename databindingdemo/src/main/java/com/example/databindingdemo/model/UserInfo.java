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

package com.example.databindingdemo.model;

import android.util.Log;

public class UserInfo {
    public String userName;
    public String userAge;
    public String userBirthday;
    private static final String TAG = "UserInfo";

    public UserInfo(String userName, String userAge, String userGender) {
        this.userName = userName;
        this.userAge = userAge;
        this.userBirthday = userGender;
    }

    public String getUserName() {
        Log.d(TAG, "getUserName: " + userName);
        return userName;
    }

    public void setUserName(String userName) {
        Log.d(TAG, "setUserName: " + userName);
        this.userName = userName;
    }
}
