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

/**
 * A wrapper for an {@link AutoCloseable} object that implements reference counting to allow
 * for resource management.
 */
public class RefCountedAutoCloseable<T extends AutoCloseable> implements AutoCloseable {
    private T mObject;
    private long mRefCount = 0;

    /**
     * Wrap the given object.
     *
     * @param object an object to wrap.
     */
    public RefCountedAutoCloseable(T object) {
        if (object == null) throw new NullPointerException();
        mObject = object;
    }

    /**
     * Increment the reference count and return the wrapped object.
     *
     * @return the wrapped object, or null if the object has been released.
     */
    public synchronized T getAndRetain() {
        if (mRefCount < 0) {
            return null;
        }
        mRefCount++;
        return mObject;
    }

    /**
     * Return the wrapped object.
     *
     * @return the wrapped object, or null if the object has been released.
     */
    public synchronized T get() {
        return mObject;
    }

    /**
     * Decrement the reference count and release the wrapped object if there are no other
     * users retaining this object.
     */
    @Override
    public synchronized void close() {
        if (mRefCount >= 0) {
            mRefCount--;
            if (mRefCount < 0) {
                try {
                    mObject.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    mObject = null;
                }
            }
        }
    }
}
