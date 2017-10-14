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

package com.okaycamera.okcamera.core.filter

import android.hardware.camera2.CameraMetadata


private var sceneHashMap: HashMap<String, Int>? = null
private var effectHashMap: HashMap<String, Int>? = null


fun getSceneMode(key: String): Int? {
    if (sceneHashMap == null || sceneHashMap!!.size === 0) {
        sceneHashMap = HashMap()
        sceneHashMap!!.put("ACTION", CameraMetadata.CONTROL_SCENE_MODE_ACTION)
        sceneHashMap!!.put("BARCODE", CameraMetadata.CONTROL_SCENE_MODE_BARCODE)
        sceneHashMap!!.put("BEACH", CameraMetadata.CONTROL_SCENE_MODE_BEACH)
        sceneHashMap!!.put("CANDLELIGHT", CameraMetadata.CONTROL_SCENE_MODE_CANDLELIGHT)
        sceneHashMap!!.put("DISABLED", CameraMetadata.CONTROL_SCENE_MODE_DISABLED)
        sceneHashMap!!.put("FACE_PRIORITY", CameraMetadata.CONTROL_SCENE_MODE_FACE_PRIORITY)
        sceneHashMap!!.put("FIREWORKS", CameraMetadata.CONTROL_SCENE_MODE_FIREWORKS)
        sceneHashMap!!.put("LANDSCAPE", CameraMetadata.CONTROL_SCENE_MODE_LANDSCAPE)
        sceneHashMap!!.put("NIGHT", CameraMetadata.CONTROL_SCENE_MODE_NIGHT)
        sceneHashMap!!.put("NIGHTPORTRAIT", CameraMetadata.CONTROL_SCENE_MODE_NIGHT_PORTRAIT)
        sceneHashMap!!.put("PARTY", CameraMetadata.CONTROL_SCENE_MODE_PARTY)
        sceneHashMap!!.put("PORTRAIT", CameraMetadata.CONTROL_SCENE_MODE_PORTRAIT)
        sceneHashMap!!.put("SNOW", CameraMetadata.CONTROL_SCENE_MODE_SNOW)
        sceneHashMap!!.put("SUNSET", CameraMetadata.CONTROL_SCENE_MODE_SUNSET)
        sceneHashMap!!.put("STEADYPHOTO", CameraMetadata.CONTROL_SCENE_MODE_STEADYPHOTO)
        sceneHashMap!!.put("SPORTS", CameraMetadata.CONTROL_SCENE_MODE_SPORTS)
        sceneHashMap!!.put("THEATRE", CameraMetadata.CONTROL_SCENE_MODE_THEATRE)
    }
    return sceneHashMap!![key]
}


fun getEffectMode(key: String): Int? {
    if (effectHashMap == null || effectHashMap!!.size === 0) {
        effectHashMap = HashMap()
        effectHashMap!!.put("AQUA", CameraMetadata.CONTROL_EFFECT_MODE_AQUA)
        effectHashMap!!.put("BLACKBOARD", CameraMetadata.CONTROL_EFFECT_MODE_BLACKBOARD)
        effectHashMap!!.put("MONO", CameraMetadata.CONTROL_EFFECT_MODE_MONO)
        effectHashMap!!.put("NEGATIVE", CameraMetadata.CONTROL_EFFECT_MODE_NEGATIVE)
        effectHashMap!!.put("POSTERIZE", CameraMetadata.CONTROL_EFFECT_MODE_POSTERIZE)
        effectHashMap!!.put("SEPIA", CameraMetadata.CONTROL_EFFECT_MODE_SEPIA)
        effectHashMap!!.put("SOLARIZE", CameraMetadata.CONTROL_EFFECT_MODE_SOLARIZE)
        effectHashMap!!.put("WHITEBOARD", CameraMetadata.CONTROL_EFFECT_MODE_WHITEBOARD)
        effectHashMap!!.put("OFF", CameraMetadata.CONTROL_EFFECT_MODE_OFF)
    }
    return effectHashMap!![key]
}


