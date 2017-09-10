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

package com.example.databindingdemo;

import android.databinding.BindingBuildInfo;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.databindingdemo.databinding.ActivityMainBinding;
import com.example.databindingdemo.model.UserInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserInfo userInfo;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView( R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        userInfo = new UserInfo("yangjianguo", "24", "1991-05-06");
        binding.setUserInfo(userInfo);
        binding.setClickListener(new ClickListener());
    }

    public class ClickListener{

        public void onClick() {
            int age = Integer.parseInt( userInfo.userAge) ;
            age++;
            Log.d(TAG, "onClick: age = " + age);
            userInfo.userAge = age + "";
            binding.setUserInfo(userInfo);
        }
    }
}
