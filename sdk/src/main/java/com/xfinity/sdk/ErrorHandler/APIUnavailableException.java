package com.xfinity.sdk.ErrorHandler;

import retrofit.RetrofitError;

public class APIUnavailableException extends Exception {
    String errorCause;

    public APIUnavailableException(RetrofitError cause) {
        errorCause = cause.getCause().getMessage();
    }

    public String getErrorCause() {
        return errorCause;
    }
}
