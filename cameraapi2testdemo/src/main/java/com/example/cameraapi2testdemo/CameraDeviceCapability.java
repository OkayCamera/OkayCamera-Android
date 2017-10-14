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

package com.example.cameraapi2testdemo;

import android.hardware.camera2.CameraCharacteristics;

import static android.hardware.camera2.CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL;
import static android.hardware.camera2.CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY;

public class CameraDeviceCapability {
    /**
     * Max preview width that is guaranteed by Camera2 API
     */
    public static final int MAX_PREVIEW_WIDTH = 1920;

    /**
     * Max preview height that is guaranteed by Camera2 API
     */
    public static final int MAX_PREVIEW_HEIGHT = 1080;
    /**
     * Check if we are using a device that only supports the LEGACY hardware level.
     * <p/>
     *
     * @return true if this is a legacy device.
     */
    public static boolean isLegacyLocked(CameraCharacteristics mCharacteristics) {
        return mCharacteristics.get(INFO_SUPPORTED_HARDWARE_LEVEL)
                == INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY;
    }
}
