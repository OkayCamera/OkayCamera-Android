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

package com.example.databindingdemo;

import android.databinding.InverseMethod;

public class DatabindingHelper {
    /*
      注解InverseMehod 用于定义于该方法作用相反的方法，在双向数据绑定中使用为了使数据能够正常的进行转化
     */
    @InverseMethod("lowercase")
    public static String capitalize(final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
        }
        return word;
    }
    @InverseMethod("capitalize")
    public static String lowercase (final String word) {
        if (word.length() > 1) {
            return String.valueOf(word.charAt(0)).toLowerCase() + word.substring(1);
        }
        return word;
    }
}
