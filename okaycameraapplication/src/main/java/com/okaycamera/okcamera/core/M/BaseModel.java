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

package com.okaycamera.okcamera.core.M;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.support.annotation.CallSuper;
import android.view.Surface;

import com.okaycamera.okcamera.core.MVVM;
import com.okaycamera.okcameralibrary.framework.OkCaptureRequestBuilder;

public abstract class BaseModel implements IModel{
    /**
     * the camera device instance which to create different session dependence different model
     */
    public CameraDevice mCameraDevice;
    public Surface mPreviewSurface;
    public MVVM.IViewModel mViewModel;

    @Override
    public void loadVM(MVVM.IViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    @CallSuper
    public void destroyModel() {
        if (mCameraDevice != null ) {
            mCameraDevice.close();
        }
    }

    public CaptureRequest buildPreviewRequest() throws CameraAccessException {
        CaptureRequest.Builder builder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
        OkCaptureRequestBuilder okBuilder = new OkCaptureRequestBuilder().init(builder);
        return okBuilder.addTarget(mPreviewSurface)
                .build();
    }
}
