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

package com.okaycamera.okcamera.module.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class OkCameraManager {
    private static OkCameraManager sCameraManager;
    private CameraHandler mCameraHandler;

    private OkCameraManager() {
        HandlerThread ht = new HandlerThread("OkCameraManager Instance handler thread");
        ht.start();
        mCameraHandler = new CameraHandler(ht.getLooper());
    }

    public static OkCameraManager instance() {
        if (sCameraManager == null) {
            sCameraManager = new OkCameraManager();

        }
        return sCameraManager;
    }

    public void sendMessageDelayed(Message msg) {
        mCameraHandler.sendMessageDelayed(msg, 2000);
    }

    public void sendMessage(Message msg) {
        mCameraHandler.sendMessage(msg);
    }

    private class CameraHandler extends Handler {
        CameraHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(final Message msg) {
            // TODO: handle message
        }
    }
}
