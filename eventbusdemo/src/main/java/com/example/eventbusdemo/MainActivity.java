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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.util.AsyncExecutor;
import org.greenrobot.eventbus.util.ThrowableFailureEvent;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 在异步发送一条消息事件
                        EventBus.getDefault().post(new MessageEvent(1, "ok"));
                    }
                }).start();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果注册过，则不能再次注册，否则会抛出异常
                if (!EventBus.getDefault().isRegistered(MainActivity.this)) {
                    // 注册EventBus
                    EventBus.getDefault().register(MainActivity.this);
                }
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消注册 EventBus
                EventBus.getDefault().unregister(MainActivity.this);
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在主线程发送一条粘性消息事件
                EventBus.getDefault().postSticky(new MessageEvent(2, "sticky message"));
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 注意此处：当打开混淆时，报错：
                 Failure event class must have a constructor with one parameter of type Throwable
                 需要在混淆文件中加入：
                 -keep class org.greenrobot.eventbus.util.ThrowableFailureEvent { *; }
                 官方文档中并没有添加该混淆，是一处错误。
                 */
                AsyncExecutor.create().execute(new AsyncExecutor.RunnableEx() {
                            @Override
                            public void run() throws Exception {
                                CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
                                boolean isSelected = checkBox.isChecked();
                                Log.d(TAG, "run: " + isSelected);
                                if (isSelected) {
                                    // 假定运行过程中抛出异常
                                    throw new Exception("AsyncExe");
                                }else {
                                    EventBus.getDefault().post(new AsncMessageEvent(3, "Exception"));
                                }
                            }
                        });
            }
        });
    }

    /**
     * 在后台（通常是单独的线程，如果发送者是异步线程，在会在发送者的线程处理，如果为主线程，则会在新的线程处理）
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void subscribeOnBackground(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeOnBackground: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
    }

    /**
     * 在主线程处理收到的消息事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeOnMainThread(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeOnMainThread: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
    }

    /**
     * 无论谁发送的消息，都会创建一个新的线程处理该消息
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void subscribeOnAsncThread(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeOnAsncThread: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, priority = 1)
    public void subscribeOnPostThread(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeOnPostThread: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
    }

    /**
     * 优先级较高的在Posting线程执行的消息
     */
    @Subscribe(threadMode = ThreadMode.POSTING, priority = 2)
    public void subscribeOnPostThread2(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeOnPostThread2: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
        /*
         事件通常是由高优先级用户取消，低优先级的订阅者将不在收到该事件。同一优先级的订阅者取消时不会影响其他订阅者
         取消仅限于在处理方法运行在发送消息的线程中。
         */
//        EventBus.getDefault().cancelEventDelivery(messageEvent);
    }

    /**
     * 粘性消息的例子，在订阅后，此方法会处理在订阅前最后一次发送的消息
     */
    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void subscribeStickyMessage(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeStickyMessage: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
    }

    /**
     * 默认为注解，默认为Post线程模式下调用
     */
    @Subscribe
    public void subscribeOnDefault(MessageEvent messageEvent) {
        Log.d(TAG, "subscribeOnDefault: what = " + messageEvent.what + "; msg = " + messageEvent.msg);
    }

    /**
     * 在主线程订阅AsyncExecutor 处理的正确结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeOnAsyncNomal(AsncMessageEvent asncMessageEvent) {
        Log.d(TAG, "subscribeOnAsyncNomal: " + asncMessageEvent);
    }

    /**
     * 在主线程订阅AsyncExecutor处理的异常结果
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeOnAsync(ThrowableFailureEvent throwableFailureEvent) {
        Log.d(TAG, "subscribeOnAsync: " + throwableFailureEvent);
    }
}
