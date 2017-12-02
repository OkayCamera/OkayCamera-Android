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

package com.okaycamera.okcameralibrary;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

public class OkTorchManager {
    private CameraManager mCameraManager;

    private CameraManager.TorchCallback mTorchCallBack = new CameraManager.TorchCallback() {
        @Override
        public void onTorchModeUnavailable(@NonNull String cameraId) {
            EventBus.getDefault().post(new TorchAvailabilityMessage(cameraId));
        }

        @Override
        public void onTorchModeChanged(@NonNull String cameraId, boolean enabled) {
            EventBus.getDefault().post(new TorchModeChangeMessage(cameraId, enabled));
        }
    };

    public OkTorchManager(Context context) {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public void setTorchMode(String cameraId, boolean enable) throws CameraAccessException {
        mCameraManager.setTorchMode(cameraId, enable);
    }

    public void registerTorchCallback(Handler handler) {
        mCameraManager.registerTorchCallback(mTorchCallBack, handler);
    }

    public void unregisterTorchCallback() {
        mCameraManager.unregisterTorchCallback(mTorchCallBack);
    }

    public class TorchAvailabilityMessage {
        public String cameraId;

        public TorchAvailabilityMessage(String id) {
            this.cameraId = id;
        }
    }

    public class TorchModeChangeMessage {
        public String cameraId;
        public boolean enabled;

        public TorchModeChangeMessage(String cameraId, boolean enabled) {
            this.cameraId = cameraId;
            this.enabled = enabled;
        }
    }
}
