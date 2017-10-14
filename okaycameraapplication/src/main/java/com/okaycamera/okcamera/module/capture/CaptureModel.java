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

package com.okaycamera.okcamera.module.capture;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.support.annotation.NonNull;
import android.view.Surface;

import com.okaycamera.okcamera.core.M.BaseModel;
import com.okaycamera.okcamera.core.M.IModel;
import com.okaycamera.okcamera.core.MVVM;

import java.util.List;

public class CaptureModel extends BaseModel {
    List<Surface> mSurfaces;

    @Override
    public IModel initModel() {
        return null;
    }

    @Override
    public void createSession() throws CameraAccessException {
        mCameraDevice.createCaptureSession(mSurfaces, new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigured(@NonNull CameraCaptureSession session) {

            }

            @Override
            public void onConfigureFailed(@NonNull CameraCaptureSession session) {

            }
        }, null);
    }

    @Override
    public void buildRequest(int type) {
        try {
            CaptureRequest.Builder builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    public void capture() {
    }

    @Override
    public void process() {
    }

    @Override
    public void saveFinalData() {
    }
}
