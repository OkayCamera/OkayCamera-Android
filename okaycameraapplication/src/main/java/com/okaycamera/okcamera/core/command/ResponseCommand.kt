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

class ResponseCommand<T, R> {

    private var execute0: Func0<R>? = null
    private var execute1: Func1<T, R>? = null

    private var canExecute0: Func0<Boolean>? = null

    /**
     * like [ReplyCommand],but ResponseCommand can return result when command has executed!
     * @param execute function to execute when event occur.
     */
    constructor(execute: Func0<R>) {
        this.execute0 = execute
    }


    constructor(execute: Func1<T, R>) {
        this.execute1 = execute
    }


    constructor(execute: Func0<R>, canExecute0: Func0<Boolean>) {
        this.execute0 = execute
        this.canExecute0 = canExecute0
    }


    constructor(execute: Func1<T, R>, canExecute0: Func0<Boolean>) {
        this.execute1 = execute
        this.canExecute0 = canExecute0
    }


    fun execute(): R? {
        if (execute0 != null && canExecute0()) {
            return execute0!!.call()
        }
        return null
    }

    private fun canExecute0(): Boolean {
        if (canExecute0 == null) {
            return true
        }
        return canExecute0!!.call()
    }


    fun execute(parameter: T): R? {
        if (execute1 != null && canExecute0()) {
            return execute1!!.call(parameter)
        }
        return null
    }
}