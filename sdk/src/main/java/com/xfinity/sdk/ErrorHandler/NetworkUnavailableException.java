package com.xfinity.sdk.ErrorHandler;

import retrofit.RetrofitError;

public class NetworkUnavailableException extends Exception {

    String errorCause;

    public NetworkUnavailableException(RetrofitError cause) {
        errorCause = cause.getCause().getMessage();
    }

    public String getErrorCause() {
        return errorCause;
    }
}
