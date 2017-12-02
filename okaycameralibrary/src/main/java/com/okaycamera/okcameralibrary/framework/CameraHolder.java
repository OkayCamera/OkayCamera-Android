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

import android.hardware.camera2.CameraDevice;

public class CameraHolder {
    /**
     * 相机的ID
     */
    private String ID;
    /**
     * 相机的实例
     */
    private CameraDevice mDevice;

    /**
     * 当前是否可用
     */
    private boolean isAvailable = false;

    private int mCameraState;

    /**
     * 相机的能力集合，通过CameraCharacteristics解析获得
     */
    private CameraCapability mCameraCapability;

    public CameraHolder(String id) {
        ID = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public CameraDevice getmDevice() {
        return mDevice;
    }

    public void setmDevice(CameraDevice mDevice) {
        this.mDevice = mDevice;
    }
}
