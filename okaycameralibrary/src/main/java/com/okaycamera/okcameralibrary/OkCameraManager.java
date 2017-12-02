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

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;

import org.greenrobot.eventbus.EventBus;

public class OkCameraManager {

    private CameraManager mCameraManager;
    private static OkCameraManager sOkCameraManager;
    private CameraHandler mCameraHandler;
    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            EventBus.getDefault().post(new CameraOpenedMessage(camera));
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            EventBus.getDefault().post(new CameraDisconnectedMessage(camera));
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            EventBus.getDefault().post(new CameraOpenErrorMessage(camera, error));
        }
    };
    private CameraManager.AvailabilityCallback mAvailabilityCallback = new CameraManager.AvailabilityCallback() {
        @Override
        public void onCameraAvailable(@NonNull String cameraId) {
            EventBus.getDefault().post(new CameraAvailableMessage(cameraId, true));
        }

        @Override
        public void onCameraUnavailable(@NonNull String cameraId) {
            EventBus.getDefault().post(new CameraAvailableMessage(cameraId, false));
        }
    };

    private OkCameraManager(Context context) {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        if (mCameraManager == null) {
            throw new RuntimeException("GET CAMERA MANAGER ERROR !");
        }

        HandlerThread ht = new HandlerThread("OkCameraManager Instance handler thread");
        ht.start();
        mCameraHandler = new CameraHandler(ht.getLooper());
    }

    /**
     * camera manager instance
     * @param context must be a application context
     * @return camera manager instance
     */
    public static OkCameraManager instance(Context context) {
        if (sOkCameraManager == null) {
            sOkCameraManager = new OkCameraManager(context);
        }
        return sOkCameraManager;
    }

    public String[] getCameraidList() throws CameraAccessException {
        return mCameraManager.getCameraIdList();
    }

    public CameraCharacteristics getCameraCharacteristics(@NonNull String cameraId) throws CameraAccessException {
        return mCameraManager.getCameraCharacteristics(cameraId);
    }

    /**
     * You can subscribe {@link CameraOpenedMessage} and {@link CameraOpenErrorMessage} to listen
     * open Camera event
     * @param cameraId which camera been opened
     * @throws CameraAccessException if dose not has the camera permission, it will throw this exception
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(Manifest.permission.CAMERA)
    public void openCamera(String cameraId) throws CameraAccessException {
        mCameraManager.openCamera(cameraId, mStateCallback, mCameraHandler);
    }

    /**
     * You can subscribe {@link CameraAvailableMessage} to listen camera availability events
     */
    public void registerAvailabilityCallback() {
        mCameraManager.registerAvailabilityCallback(mAvailabilityCallback, mCameraHandler);
    }

    public void unregisterAvailabilityCallback() {
        mCameraManager.unregisterAvailabilityCallback(mAvailabilityCallback);
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

    // ================================= some messages =============================================

    public class CameraAvailableMessage {
        public String cameraId;
        public boolean available;
        public CameraAvailableMessage(String cameraId, boolean available) {
            this.cameraId = cameraId;
            this.available = available;
        }
    }

    public class CameraOpenErrorMessage {
        public CameraDevice cameraDevice;
        public int error;
        public CameraOpenErrorMessage(CameraDevice cameraDevice, int error) {
            this.cameraDevice = cameraDevice;
            this.error = error;
        }
    }

    public class CameraOpenedMessage {
        public CameraDevice cameraDevice;
        public CameraOpenedMessage(CameraDevice cameraDevice) {
            this.cameraDevice = cameraDevice;
        }
    }

    public class CameraDisconnectedMessage {
        public CameraDevice cameraDevice;

        public CameraDisconnectedMessage(CameraDevice cameraDevice) {
            this.cameraDevice = cameraDevice;
        }
    }
}
