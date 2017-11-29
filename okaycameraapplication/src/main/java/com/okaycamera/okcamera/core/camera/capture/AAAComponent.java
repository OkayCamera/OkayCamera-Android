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
// 3A控制模式

import android.support.annotation.IntRange;

import static android.hardware.camera2.CameraMetadata.CONTROL_AE_ANTIBANDING_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_OFF;
import static android.hardware.camera2.CaptureRequest.CONTROL_MODE;

// 自动白平衡锁
import static android.hardware.camera2.CaptureRequest.CONTROL_AWB_LOCK;
// 自动白平衡模式
import static android.hardware.camera2.CaptureRequest.CONTROL_AWB_MODE;
// 自动白平衡区域
import static android.hardware.camera2.CaptureRequest.CONTROL_AWB_REGIONS;

// 自动对焦模式
import static android.hardware.camera2.CaptureRequest.CONTROL_AF_MODE;
// 自动对焦区域
import static android.hardware.camera2.CaptureRequest.CONTROL_AF_REGIONS;
// 是否触发这个请求的自动对焦。
import static android.hardware.camera2.CaptureRequest.CONTROL_AF_TRIGGER;


// 自动曝光下的抖动补偿模式， 'auto', '50hz', '60hz'
// see https://stackoverflow.com/questions/7254267/what-is-antibanding-in-photographing
//import static android.hardware.camera2.CaptureRequest.CONTROL_AE_ANTIBANDING_MODE;
// 调整自动曝光下图像亮度
import static android.hardware.camera2.CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION;
// AE锁定
import static android.hardware.camera2.CaptureRequest.CONTROL_AE_LOCK;
// AE模式，'OFF','ON','ON_AUTO_FLASH','ON_ALWAYS_FLASH','ON_AUTO_FLASH_REDEYE'
import static android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE;
// 在处理该request前，是否控制启动一个测光序列
import static android.hardware.camera2.CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER;
// 用于自动曝光的测量区域列表
import static android.hardware.camera2.CaptureRequest.CONTROL_AE_REGIONS;
// 自动曝光程序可以调整的捕获帧率范围以保持良好曝光
import static android.hardware.camera2.CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE;


// 当前request的3A捕获策略
import static android.hardware.camera2.CaptureRequest.CONTROL_CAPTURE_INTENT;

/**
 * 3A 相关的组件，主要维护和存放3A相关的拍摄参数
 */
public class AAAComponent {


    /**
     * 防闪烁频率
     *
     * 对应的CaptureRequest Key为 android.hardware.camera2.CaptureRequest.CONTROL_AE_ANTIBANDING_MODE
     */
    public int mAEAntibandingMode = CONTROL_AE_ANTIBANDING_MODE_OFF;

    public int getmAEAntibandingMode() {
        return mAEAntibandingMode;
    }

    public void setmAEAntibandingMode(int mAEAntibandingMode) {
        this.mAEAntibandingMode = mAEAntibandingMode;
    }


    public int mAEMode = CONTROL_AE_MODE_OFF;

    @IntRange() public int mControlAETargetFpsRange = 30;

}
