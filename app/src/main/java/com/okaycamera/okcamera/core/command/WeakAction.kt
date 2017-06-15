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

package com.okaycamera.okcamera.core.command

import java.lang.ref.WeakReference

public class WeakAction<T> {
    var action: Action0? = null
        private set // the setter is private and has the default implementation
    var action1: Action1<T>? = null
        private set // the setter is private and has the default implementation

    private var reference: WeakReference<T>? = null

    constructor(target: T, action: Action0) {
        reference = WeakReference(target)
        this.action = action
    }

    constructor(target: T, action1: Action1<T>) {
        reference = WeakReference(target)
        this.action1 = action1
    }

    fun execute() {
        if (action != null && isLive()) {
            action!!.call()
        }
    }

    fun execute(param: T) {
        if (action1 != null && isLive()) {
            action1!!.call(param)
        }
    }

    fun markForDeletion() {
        reference!!.clear()
        reference = null
        action = null
        action1 = null
    }

    private fun isLive(): Boolean {
        return !(reference == null || reference!!.get() == null)
    }

    fun getTarget(): Any? {
        return if (reference != null) reference!!.get() else null
    }
}