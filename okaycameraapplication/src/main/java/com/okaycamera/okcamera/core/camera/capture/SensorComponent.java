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

// 传感器读出数据区域
import static android.hardware.camera2.CaptureRequest.SCALER_CROP_REGION;
// 每个像素的曝光时间
import static android.hardware.camera2.CaptureRequest.SENSOR_EXPOSURE_TIME;
// 从帧曝光开始到下一帧曝光开始的持续时间。
import static android.hardware.camera2.CaptureRequest.SENSOR_FRAME_DURATION;
// 在处理图像之前，应用于传感器的一系列增益量
import static android.hardware.camera2.CaptureRequest.SENSOR_SENSITIVITY;
// 在测试模式下，当SENSOR_TEST_PATTERN_MODE 为 SOLID_COLOR 时的测试像素[R, G_even, G_odd, B] 值
import static android.hardware.camera2.CaptureRequest.SENSOR_TEST_PATTERN_DATA;
// 当启用时，传感器发送一个测试模式，而不是从相机进行真正的曝光。
import static android.hardware.camera2.CaptureRequest.SENSOR_TEST_PATTERN_MODE;

public class SensorComponent {


}
