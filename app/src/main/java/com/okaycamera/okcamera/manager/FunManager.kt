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
package com.okaycamera.okcamera.manager

object FunManager{
    /**
     * whether open lunch animation
     */
    fun useLunchAnim():Boolean{
        return true;
    }

    /**
     * which logger util has been used by this application
     * should not been modified by dynamic
     */
    fun useWhichLogger():Int {
        // use android.util.Log with default
        return 0;
    }
}
