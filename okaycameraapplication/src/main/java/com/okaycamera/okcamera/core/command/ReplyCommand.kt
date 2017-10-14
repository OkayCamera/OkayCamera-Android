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

class ReplyCommand<T> {

    private var execute0: Action0? = null
    private var execute1: Action1<T>? = null
    private var canExecute0: Func0<Boolean>? = null

    /**
     * constructor method with one Action no parameter to call
     */
    constructor(execute: Action0) {
        this.execute0 = execute
    }

    /**
     * constructor method with one Action one parameter< T > to call
     */
    constructor(execute: Action1<T>) {
        this.execute1 = execute
    }

    /**
     * @param execute callback for event
     * @param canExecute0 if this function return true the action execute would be invoked!
     * otherwise would't invoked!
     */
    constructor(execute: Action0, canExecute0: Func0<Boolean>) {
        this.execute0 = execute
        this.canExecute0 = canExecute0
    }

    /**
     * @param execute callback for event,this callback need a params
     * @param canExecute0 if this function return true the action execute would be invoked!
     * otherwise would't invoked!
     */
    constructor(execute: Action1<T>, canExecute0: Func0<Boolean>) {
        this.execute1 = execute
        this.canExecute0 = canExecute0
    }

    /**
     * run action without parameter if cam execute
     */
    fun execute() {
        if (execute0 != null && canExecute0()) {
            execute0!!.call()
        }
    }

    /**
     * run action with parameter if cam execute
     */
    fun execute(parameter: T) {
        if (execute1 != null && canExecute0()) {
            execute1!!.call(parameter)
        }
    }

    private fun canExecute0(): Boolean {
        if (canExecute0 == null) {
            return true
        }
        return canExecute0!!.call()
    }
}