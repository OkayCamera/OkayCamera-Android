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

package com.okaycamera.okcameralibrary.capture;
// 3A控制模式

import android.support.annotation.IntRange;

import static android.hardware.camera2.CameraMetadata.CONTROL_AE_ANTIBANDING_MODE_OFF;
import static android.hardware.camera2.CameraMetadata.CONTROL_AE_MODE_OFF;

// 自动白平衡锁
// 自动白平衡模式
// 自动白平衡区域

// 自动对焦模式
// 自动对焦区域
// 是否触发这个请求的自动对焦。


// 自动曝光下的抖动补偿模式， 'auto', '50hz', '60hz'
// see https://stackoverflow.com/questions/7254267/what-is-antibanding-in-photographing
//import static android.hardware.camera2.CaptureRequest.CONTROL_AE_ANTIBANDING_MODE;
// 调整自动曝光下图像亮度
// AE锁定
// AE模式，'OFF','ON','ON_AUTO_FLASH','ON_ALWAYS_FLASH','ON_AUTO_FLASH_REDEYE'
// 在处理该request前，是否控制启动一个测光序列
// 用于自动曝光的测量区域列表
// 自动曝光程序可以调整的捕获帧率范围以保持良好曝光


// 当前request的3A捕获策略


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
