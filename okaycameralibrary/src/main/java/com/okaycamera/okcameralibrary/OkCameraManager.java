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

/**
 * This class is a single instance class, to hack CameraManger use event bus
 *
 * @author jianguo yang
 * @version 0.0.1
 */
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

    private class CameraHandler extends Handler {
        CameraHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(final Message msg) {
            // TODO: handle message
        }
    }

    private OkCameraManager(Context context) {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        if (mCameraManager == null) {
            throw new RuntimeException("GET CAMERA MANAGER ERROR !");
        }

        HandlerThread ht = new HandlerThread("OkCameraManager Instance handler thread");
        ht.start();
        mCameraHandler = new CameraHandler(ht.getLooper());

        // TODO: 2018/2/4 maybe somewhere need to call ht.quitSafely() to quit the thread
    }

    /**
     * The camera manager instance
     * @param context must be a application context
     * @return camera manager instance
     */
    public static OkCameraManager instance(Context context) {
        if (sOkCameraManager == null) {
            sOkCameraManager = new OkCameraManager(context);
        }
        return sOkCameraManager;
    }

    /**
     * a proxy of {@link CameraManager#getCameraIdList()}
     *
     * Return the list of currently connected camera devices by identifier, including
     * cameras that may be in use by other camera API clients.
     *
     * <p>Non-removable cameras use integers starting at 0 for their
     * identifiers, while removable cameras have a unique identifier for each
     * individual device, even if they are the same model.</p>
     *
     * @see CameraManager#getCameraIdList()
     * @return The list of currently connected camera devices.
     * @throws CameraAccessException
     */
    public String[] getCameraIdList() throws CameraAccessException {
        return mCameraManager.getCameraIdList();
    }

    /**
     * <p>Query the capabilities of a camera device. These capabilities are
     * immutable for a given camera.</p>
     *
     * @param cameraId The id of the camera device to query
     * @return The properties of the given camera
     *
     * @throws IllegalArgumentException if the cameraId does not match any
     *         known camera device.
     * @throws CameraAccessException if the camera device has been disconnected.
     *
     * @see #getCameraIdList
     * @see android.app.admin.DevicePolicyManager#setCameraDisabled
     */
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
     *
     * @see CameraAvailableMessage
     */
    public void registerCameraAvailability() {
        mCameraManager.registerAvailabilityCallback(mAvailabilityCallback, mCameraHandler);
    }


    /**
     * The pair method of {@link #registerCameraAvailability()}
     *
     * @see #registerCameraAvailability()
     */
    public void unregisterCameraAvailability() {
        mCameraManager.unregisterAvailabilityCallback(mAvailabilityCallback);
    }

    /**
     * send a message to camera manager thread's handler
     */
    public void sendMessageDelayed(Message msg, long delayTime) {
        mCameraHandler.sendMessageDelayed(msg, delayTime);
    }

    /**
     * send a message to camera manager thread's handler immediately
     */
    public void sendMessageImmediately(Message msg) {
        mCameraHandler.sendMessage(msg);
    }

    // ================================= some messages =============================================

    /**
     * The Event bus message for listen camera device available
     */
    public class CameraAvailableMessage {
        public String cameraId;
        public boolean available;
        public CameraAvailableMessage(String cameraId, boolean available) {
            this.cameraId = cameraId;
            this.available = available;
        }
    }

    /**
     * The Event bus message for listen camera device open error
     */
    public class CameraOpenErrorMessage {
        public CameraDevice cameraDevice;
        public int error;
        public CameraOpenErrorMessage(CameraDevice cameraDevice, int error) {
            this.cameraDevice = cameraDevice;
            this.error = error;
        }
    }

    /**
     * The Event bus message for listen camera device opened
     */
    public class CameraOpenedMessage {
        public CameraDevice cameraDevice;
        public CameraOpenedMessage(CameraDevice cameraDevice) {
            this.cameraDevice = cameraDevice;
        }
    }

    /**
     * The Event bus message for listen camera device disconnect
     */
    public class CameraDisconnectedMessage {
        public CameraDevice cameraDevice;

        public CameraDisconnectedMessage(CameraDevice cameraDevice) {
            this.cameraDevice = cameraDevice;
        }
    }
}
