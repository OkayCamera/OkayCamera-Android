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

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.databindingdemo.databinding.ActivityMainBinding;
import com.example.databindingdemo.databinding.IncludeLayoutBinding;
import com.example.databindingdemo.databinding.ViewStubBinding;
import com.example.databindingdemo.model.DetailedInformation;
import com.example.databindingdemo.model.UserInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserInfo userInfo;
    private ActivityMainBinding binding;

    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定方法一：
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 绑定方法二：
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 初始化UserInfo 这个Module
        userInfo = new UserInfo("yangjianguo", "24", "1991-05-06");
        binding.setUserInfo(userInfo);
        binding.setClickListener(new ClickListener());

        // 初始化 DetailedInformation 这个Module
        DetailedInformation df = new DetailedInformation();
        df.userName.set(userInfo.getUserName());
        df.userAge.set(Integer.valueOf(userInfo.userAge));
        // 在activity_main 中 include 的layout 由于是使用databinding定义的View，因此本质上属于
        // IncludeLayoutBinding
        IncludeLayoutBinding includeLayoutBinding = binding.includeLayout;
        includeLayoutBinding.setDetailInfo(df);

        // 依旧可以使用findViewById()访问view， 但是拒绝这么使用，因为binding中将所有带id的view都进行了实例化
        // name = (TextView) findViewById(R.id.textView);

        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub viewStub, View view) {
                // 在被其他布局inflate时，将对应的值绑定上去
                ViewStubBinding binding = DataBindingUtil.bind(view);
                String testText = "testText balala";
                binding.setTestText(testText);
            }
        });

        // 在合适的时间 inflate viewStub
        binding.viewStub.getViewStub().inflate();
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
