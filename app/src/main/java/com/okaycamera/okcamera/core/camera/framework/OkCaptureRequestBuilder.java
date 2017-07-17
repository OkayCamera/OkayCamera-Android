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

package com.okaycamera.okcamera.core.camera.framework;

import android.hardware.camera2.CaptureRequest;
import android.support.annotation.NonNull;
import android.view.Surface;

import java.util.List;

public class OkCaptureRequestBuilder {
    private CaptureRequest.Builder mBuilder;

    public  OkCaptureRequestBuilder(@NonNull CaptureRequest.Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public  OkCaptureRequestBuilder(@NonNull CaptureRequest.Builder mBuilder, Object tag) {
        this.mBuilder = mBuilder;
        this.setTag(tag);
    }

    /**
     * Set a tag for this request.
     * @param tag an arbitrary Object to store with this request
     */
    public OkCaptureRequestBuilder 	setTag(@NonNull Object tag) {
        mBuilder.setTag(tag);
        return this;
    }

    /**
     * Add a surface to the list of targets for this request,
     * The Surface added must be one of the surfaces included in the most recent call to createCaptureSession(List,
     * CameraCaptureSession.StateCallback, Handler), when the request is given to the camera device.
     * @param outputTarget Surface to use as an output target for this request
     * @see CaptureRequest.Builder#addTarget(Surface)
     */
    @Deprecated
    public OkCaptureRequestBuilder addTarget(@NonNull Surface outputTarget) {
        mBuilder.addTarget(outputTarget);
        return this;
    }

    /**
     * Add a surface to the list of targets for this request
     * @param outputs  the surfaces included in the most recent call to createCaptureSession(List,
     *                CameraCaptureSession.StateCallback, Handler)
     * @param targetIndexArray which suface in outputs
     */
    public OkCaptureRequestBuilder addTarget(@NonNull List<Surface> outputs, @NonNull int[] targetIndexArray) {
        for (int aTargetIndexArray : targetIndexArray) {
            addTarget(outputs.get(aTargetIndexArray));
        }
        return this;
    }

    public OkCaptureRequestBuilder 	removeTarget(@NonNull Surface outputTarget) {
        mBuilder.removeTarget(outputTarget);
        return this;
    }

    /**
     * Set a capture request field to a value. The field definitions can be found in CaptureRequest.
     * @param key which field definitions in {@link CaptureRequest}
     */
    public <T> OkCaptureRequestBuilder set(@NonNull CaptureRequest.Key<T> key, T value) {
        mBuilder.set(key,value);
        return this;
    }

    /**
     * Build a request using the current target Surfaces and settings.
     * @return CaptureRequest
     */
    public CaptureRequest build() {
        return mBuilder.build();
    }

    public void clear() {

    }

    public void reset() {

    }

    public <T> T get(CaptureRequest.Key<T> key) {
        return mBuilder.get(key);
    }
}
