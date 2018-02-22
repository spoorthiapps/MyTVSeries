package com.xfinity.sdk.factory;

import com.xfinity.sdk.api.XfinityDemoSDK;
import com.xfinity.sdk.api.XfinityLiveSDK;
import com.xfinity.sdk.api.XfinitySDKIF;
import com.xfinity.sdk.enums.APITYPE;

public class XfinitySDKFactory {

    public XfinitySDKIF createXfinitySDK(APITYPE apitype) {
         return createInstance(apitype);
    }

    private static XfinitySDKIF createInstance(APITYPE apitype) {
        switch (apitype) {
            case DEMO:
                return new XfinityDemoSDK();
            default:
                return new XfinityLiveSDK();
        }
    }
}
