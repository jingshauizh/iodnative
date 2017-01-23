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

package com.mvp.jingshuai.data.network;

import com.mvp.jingshuai.data.model.InfoObjectModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiService {



    @GET("/portal-avalanche-iod-backend-war-15.3.9/iot/user_feed/{userId}.json")
    Call<InfoResponse> userFeed(@Path("userId") long userId, @Query("since") long since);


    @GET("/portal-avalanche-iod-backend-war-15.3.9/iod/infoobjects")
    Call<InfoResponse> allinfoObjects(@Query("vodId") String vodId, @Query("lang") String lang);

    @GET("/portal-avalanche-iod-backend-war-15.3.9/iod/infoobjects/{infoid}")
    Call<InfoObjectModel> getInfoObject(@Path("infoid") String infoid, @Query("vodId") String vodId, @Query("lang") String lang);



}


