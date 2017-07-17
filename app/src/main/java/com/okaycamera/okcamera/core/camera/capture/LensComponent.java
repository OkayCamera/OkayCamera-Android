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

package com.okaycamera.okcamera.core.camera.capture;

import android.hardware.camera2.CaptureRequest;

import static android.hardware.camera2.CameraMetadata.LENS_OPTICAL_STABILIZATION_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.LENS_OPTICAL_STABILIZATION_MODE_ON;
import static android.hardware.camera2.CaptureRequest.LENS_APERTURE;
import static android.hardware.camera2.CaptureRequest.LENS_FILTER_DENSITY;
import static android.hardware.camera2.CaptureRequest.LENS_FOCAL_LENGTH;
import static android.hardware.camera2.CaptureRequest.LENS_FOCUS_DISTANCE;
import static android.hardware.camera2.CaptureRequest.LENS_OPTICAL_STABILIZATION_MODE;

public class LensComponent {
    // 光圈值
    public static final CaptureRequest.Key<Float> KEY_APERTURE = LENS_APERTURE;
    // 中密度滤镜
    public static final CaptureRequest.Key<Float> KEY_FILTER_DENSITY = LENS_FILTER_DENSITY;
    // 光学变焦的焦距值
    public static final CaptureRequest.Key<Float> KEY_FOCAL_LENGTH = LENS_FOCAL_LENGTH;
    // 焦平面距离
    public static final CaptureRequest.Key<Float> KEY_FOCUS_DISTANCE = LENS_FOCUS_DISTANCE;
    // 光学影像防抖模式
    public static final CaptureRequest.Key<Integer> KEY_OPTICAL_STABILIZATION_MODE = LENS_OPTICAL_STABILIZATION_MODE;

    public static final int VALUE_OPTICAL_STABILIZATION_MODE_ON = LENS_OPTICAL_STABILIZATION_MODE_ON;
    public static final int VALUE_OPTICAL_STABILIZATION_MODE_OFF = LENS_OPTICAL_STABILIZATION_MODE_OFF;

}
