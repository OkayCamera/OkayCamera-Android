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

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

public class EventBusApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // 配置EventBus
        configEventBus();
    }

    public void configEventBus(){
        /*
         这些配置是可选项，也可不配置,如果配置，一定要放到application生命周期中，Activity生命周期中会出现异常，详见：
         http://greenrobot.org/eventbus/documentation/configuration/
         http://greenrobot.org/files/eventbus/javadoc/3.0/
         */
        EventBus.builder()
                .addIndex(new MyEventBusIndex())
                .logNoSubscriberMessages(false)
                .logSubscriberExceptions(false)
                .sendNoSubscriberEvent(false)
                .strictMethodVerification(true)
                /*
                 by default, EventBus catches exceptions thrown from subscriber methods and sends
                 a SubscriberExceptionEvent that may, but does not have to, be handled.
                 */
                .throwSubscriberException(true)
                /*
                 this can be done only once before the default EventBus instance is used the first
                 time. Subsequent calls to  installDefaultEventBus() will throw an exception.
                 */
                .installDefaultEventBus();
    }
}
