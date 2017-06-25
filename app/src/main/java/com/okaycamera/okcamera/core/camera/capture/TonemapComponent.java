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

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.TonemapCurve;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.okaycamera.okcamera.core.camera.framework.OkCaptureRequestBuilder;

//  当 TONEMAP_MODE 为 CONTRAST_CURVE时， 所使用的色调映射/对比度/Gamma曲线值。
import static android.hardware.camera2.CaptureRequest.TONEMAP_CURVE;

//  当 TONEMAP_MODE 为 PRESET_CURVE 时，所使用的预设的色调映射曲线
import static android.hardware.camera2.CameraMetadata.TONEMAP_PRESET_CURVE_REC709;
import static android.hardware.camera2.CameraMetadata.TONEMAP_PRESET_CURVE_SRGB;

import static android.hardware.camera2.CameraCharacteristics.TONEMAP_AVAILABLE_TONE_MAP_MODES;

import static android.hardware.camera2.CaptureRequest.TONEMAP_MODE;
// values
import static android.hardware.camera2.CameraMetadata.TONEMAP_MODE_CONTRAST_CURVE;
import static android.hardware.camera2.CameraMetadata.TONEMAP_MODE_FAST;
import static android.hardware.camera2.CameraMetadata.TONEMAP_MODE_HIGH_QUALITY;
import static android.hardware.camera2.CameraMetadata.TONEMAP_MODE_GAMMA_VALUE;
import static android.hardware.camera2.CameraMetadata.TONEMAP_MODE_PRESET_CURVE;


/**
 * 色泽贴图(tonemap) ，和HDR相关的一个设置, 全局的对比度/伽马/色调映射控制模式
 */
public class TonemapComponent implements IComponent {
    public static final int FAST = TONEMAP_MODE_FAST;
    public static final int HIGH_QUALITY = TONEMAP_MODE_HIGH_QUALITY;
    public static final int CONTRAST_CURVE = TONEMAP_MODE_CONTRAST_CURVE;
    public static final int GAMMA_VALUE = TONEMAP_MODE_GAMMA_VALUE;
    public static final int PRESET_CURVE = TONEMAP_MODE_PRESET_CURVE;

    private OkCaptureRequestBuilder mBuilder;
    private int mPresetCurveValue = -1;
    private float mGammaCurveValue = 0.0f;
    private TonemapCurve mTonemapCurve;

    public TonemapComponent(OkCaptureRequestBuilder builder) {
        mBuilder = builder;
    }

    @Override
    public int getCurrentModeValue() {
        return mBuilder.get(TONEMAP_MODE);
    }

    @Override
    public void setModeValue(int mode) {
        mBuilder.set(TONEMAP_MODE, mode);
    }

    @Override
    public OkCaptureRequestBuilder submit() {
            switch (getCurrentModeValue()) {
                case CONTRAST_CURVE:
                    if (mTonemapCurve != null) {
                        mBuilder.set(TONEMAP_CURVE, mTonemapCurve);
                    }
                    break;
                case  PRESET_CURVE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mPresetCurveValue != -1) {
                        mBuilder.set(android.hardware.camera2.CaptureRequest.TONEMAP_PRESET_CURVE, mPresetCurveValue);
                    }
                    break;
                case GAMMA_VALUE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mGammaCurveValue != -1) {
                        mBuilder.set(android.hardware.camera2.CaptureRequest.TONEMAP_GAMMA, mGammaCurveValue);
                    }
                    break;
            }
            return mBuilder;
    }

    public void setSubModeValue(int mode, Object value) {
        switch (mode) {
            case CONTRAST_CURVE:
                mTonemapCurve = (TonemapCurve) value;
                break;
            case PRESET_CURVE:
                mPresetCurveValue = (int) value;
                break;
            case GAMMA_VALUE:
                /*
                 * 当 TONEMAP_MODE 为 GAMMA_VALUE 时，所使用的色调映射曲线值,* OUT = pow(IN, 1.0 / gamma),
                 * gamma 的值在不同设备上有所不同，但是1-5之内的不会被剪掉
                 */
                mGammaCurveValue = (float) value;
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int[] getPresetCurveValues() {
        return new int[]{TONEMAP_PRESET_CURVE_REC709, TONEMAP_PRESET_CURVE_SRGB};
    }

    @Override
    public int[] getAvailableModeList(CameraCharacteristics characteristics) {
        return characteristics.get(TONEMAP_AVAILABLE_TONE_MAP_MODES);
    }

}
