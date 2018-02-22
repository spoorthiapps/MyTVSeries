package com.xfinity.sdk.ErrorHandler;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class XfinityAPIErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        if (cause.getKind() == RetrofitError.Kind.NETWORK) {
            return exceptionForNetworkError(cause);
        } else {
            return exceptionForAPIError(cause);
        }
    }

    private Throwable exceptionForAPIError(RetrofitError cause) {
        return new APIUnavailableException(cause);
    }

    private Throwable exceptionForNetworkError(RetrofitError cause) {
        return new NetworkUnavailableException(cause);
    }
}
