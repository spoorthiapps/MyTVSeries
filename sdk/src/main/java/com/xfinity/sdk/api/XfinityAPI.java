package com.xfinity.sdk.api;

import com.xfinity.sdk.ErrorHandler.APIUnavailableException;
import com.xfinity.sdk.ErrorHandler.NetworkUnavailableException;
import com.xfinity.sdk.response.ProgramsResponse;

import retrofit.http.GET;
import retrofit.http.Query;

public interface XfinityAPI {
    @GET("/api/xfinity/ipad/home/videos")
    ProgramsResponse getPrograms(@Query("filter") String filter, @Query("type") String type) throws NetworkUnavailableException, APIUnavailableException;
}
