package com.xfinity.sdk.ProgramDataListener;

import com.xfinity.sdk.response.BaseResponse;

public interface BaseResponseListener {
    public void onSuccess(BaseResponse response);
    public void onFailure();
}
