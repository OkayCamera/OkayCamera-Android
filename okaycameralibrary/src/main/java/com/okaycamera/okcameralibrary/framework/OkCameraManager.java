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

package com.okaycamera.okcameralibrary.framework;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;

import java.util.Map;

public class OkCameraManager {
    private CameraManager mCameraManager;

    private Map<String, CameraHolder> mCameraHolderMap;

    /**
     * 这个线程用于处理Camera是否可用的回调
     */
    private HandlerThread mHandlerThread = new HandlerThread("CameraAvailibilityListener", 0);
    private Handler mHandler = new Handler(mHandlerThread.getLooper());

    private CameraManager.AvailabilityCallback mCameraAvailibilityCallback = new CameraManager.AvailabilityCallback() {
        @Override
        public void onCameraAvailable(@NonNull String cameraId) {
            if (mCameraHolderMap != null) {
                mCameraHolderMap.get(cameraId).setAvailable(true);
            }
        }

        @Override
        public void onCameraUnavailable(@NonNull String cameraId) {
            if (mCameraHolderMap != null) {
                mCameraHolderMap.get(cameraId).setAvailable(false);
            }
        }
    };

    public OkCameraManager(Context context) throws CameraAccessException {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        String[] cameraList = getCameraList();
        for(int i = 0; i < cameraList.length; i++) {
            mCameraHolderMap.put(cameraList[i], new CameraHolder(cameraList[i]));
        }
    }

    public String[] getCameraList() throws CameraAccessException {
        return mCameraManager.getCameraIdList();
    }

    public Map<String, CameraHolder> getmCameraHolderMap() {
        return mCameraHolderMap;
    }

    public void registerCameraAvailabilityCallback() {
        mHandlerThread.start();
        mCameraManager.registerAvailabilityCallback(mCameraAvailibilityCallback, mHandler);
    }

    public void unregisterCameraAvailibilityCallback() {
        mCameraManager.unregisterAvailabilityCallback(mCameraAvailibilityCallback);
        mHandlerThread.quitSafely();
    }
}
