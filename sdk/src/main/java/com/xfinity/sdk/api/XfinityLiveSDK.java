package com.xfinity.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xfinity.sdk.ErrorHandler.APIUnavailableException;
import com.xfinity.sdk.ErrorHandler.NetworkUnavailableException;
import com.xfinity.sdk.ErrorHandler.XfinityAPIErrorHandler;
import com.xfinity.sdk.ProgramDataListener.BaseResponseListener;
import com.xfinity.sdk.response.ProgramsResponse;
import com.xfinity.sdk.serializer.CustomDeserializer;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class XfinityLiveSDK implements XfinitySDKIF {

    XfinityAPI xfinityAPI;
    private final String BASE_END_POINT = "http://xfinitytv.comcast.net";
    private final String JSON_QUERY_PARAMETER_TEXT = "json";

    public XfinityLiveSDK() {
        xfinityAPI = buildXfinityRemoteAPI();
    }

    private XfinityAPI buildXfinityRemoteAPI() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(BASE_END_POINT);
        builder.setLogLevel(RestAdapter.LogLevel.FULL);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ProgramsResponse.VideoGalleries.class, new CustomDeserializer());
        Gson gson = gsonBuilder.create();
        GsonConverter converter = new GsonConverter(gson);
        builder.setConverter(converter);

        XfinityAPIErrorHandler errorHandler = new XfinityAPIErrorHandler();
        builder.setErrorHandler(errorHandler);

        RestAdapter restAdapter = builder.build();
        return restAdapter.create(XfinityAPI.class);
    }

    @Override
    public void getXfinityProgramData(BaseResponseListener baseResponseListener) {
        try {
            ProgramsResponse response = xfinityAPI.getPrograms("", JSON_QUERY_PARAMETER_TEXT);
            baseResponseListener.onSuccess(response);
        } catch (NetworkUnavailableException e) {
            handleFailureScenario(baseResponseListener, e.getMessage());
        } catch (APIUnavailableException e) {
            handleFailureScenario(baseResponseListener, e.getMessage());
        }
    }

    private void handleFailureScenario(BaseResponseListener baseResponseListener, String errorCause) {
        baseResponseListener.onFailure();
    }
}
