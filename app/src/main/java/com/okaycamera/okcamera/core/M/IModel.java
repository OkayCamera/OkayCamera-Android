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
import android.hardware.camera2.CaptureRequest;

import com.okaycamera.okcamera.core.MVVM;

public interface IModel extends MVVM.IModel{
    /* life cycle */
    IModel initModel();

    /* event process */
    void loadVM(MVVM.IViewModel viewModel);
    void createSession() throws CameraAccessException;
    CaptureRequest buildPreviewRequest() throws CameraAccessException;
    void buildRequest(int type);
    void process();
    void saveFinalData();

    /* other */
    void destroyModel();
}
