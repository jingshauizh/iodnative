/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mvp.jingshuai.android_iod.event;

import android.support.annotation.Nullable;

import com.mvp.jingshuai.data.model.InfoObjectModel;

import java.util.List;

public class FetchedCurrentInfoEvent {

    private final boolean mSuccess;
    @Nullable
    private final List<InfoObjectModel> mOldest;

    public FetchedCurrentInfoEvent(boolean success, @Nullable List<InfoObjectModel> oldest) {
        mSuccess = success;
        mOldest = oldest;
    }

    public boolean isSuccess() {
        return mSuccess;
    }



    @Nullable
    public List<InfoObjectModel> getOldest() {
        return mOldest;
    }
}
