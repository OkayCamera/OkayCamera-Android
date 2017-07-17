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
import static android.hardware.camera2.CaptureRequest.BLACK_LEVEL_LOCK;
// 色差校正算法的运算模式
import static android.hardware.camera2.CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE;
// 白平衡Bayer raw 颜色通道增益
import static android.hardware.camera2.CaptureRequest.COLOR_CORRECTION_GAINS;
// 从Raw到sRGB颜色转化的控制模式
import static android.hardware.camera2.CaptureRequest.COLOR_CORRECTION_MODE;
// 从传感器RGB颜色模式向sRGB颜色模式转换的转换矩阵
import static android.hardware.camera2.CaptureRequest.COLOR_CORRECTION_TRANSFORM;


// 颜色模式
import static android.hardware.camera2.CaptureRequest.CONTROL_EFFECT_MODE;
//import static android.hardware.camera2.CaptureRequest.CONTROL_ENABLE_ZSL;

// 捕获原始传感器数据后输出图像的附加灵敏度提升量。
import static android.hardware.camera2.CaptureRequest.CONTROL_POST_RAW_SENSITIVITY_BOOST;
// 情景模式
import static android.hardware.camera2.CaptureRequest.CONTROL_SCENE_MODE;
// 视频防抖模式
import static android.hardware.camera2.CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE;

// 边缘增强模式
import static android.hardware.camera2.CaptureRequest.EDGE_MODE;
// 闪光灯模式
import static android.hardware.camera2.CaptureRequest.FLASH_MODE;
// 热像素矫正的模式
import static android.hardware.camera2.CaptureRequest.HOT_PIXEL_MODE;

import static android.hardware.camera2.CaptureRequest.NOISE_REDUCTION_MODE;
// 原始输出帧的曝光时间增量因子
import static android.hardware.camera2.CaptureRequest.REPROCESS_EFFECTIVE_EXPOSURE_FACTOR;

// 镜头阴影校正量
import static android.hardware.camera2.CaptureRequest.SHADING_MODE;
// 用于人脸检测模式，可选
import static android.hardware.camera2.CaptureRequest.STATISTICS_FACE_DETECT_MODE;
//  热像素图模式，可选
import static android.hardware.camera2.CaptureRequest.STATISTICS_HOT_PIXEL_MAP_MODE;
//  相机设备是否将在输出结果元数据中输出镜头着色映射。
import static android.hardware.camera2.CaptureRequest.STATISTICS_LENS_SHADING_MAP_MODE;
public class StandardComponent {

}
