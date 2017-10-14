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

package com.okaycamera.okcamera.module.video;

import android.hardware.camera2.CameraAccessException;

import com.okaycamera.okcamera.core.M.BaseModel;
import com.okaycamera.okcamera.core.M.IModel;

public class VideoModel extends BaseModel {
    @Override
    public IModel initModel() {
        return null;
    }

    @Override
    public void createSession() throws CameraAccessException {

    }

    @Override
    public void buildRequest(int type) {

    }

    @Override
    public void process() {

    }

    @Override
    public void saveFinalData() {

    }

    // video's operations
    public void startRecord(){

    }

    public void stopRecord() {

    }

    public void pauseRecord() {

    }

    public void snapShot() {

    }


}
