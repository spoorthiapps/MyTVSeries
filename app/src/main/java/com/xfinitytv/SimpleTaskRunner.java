package com.xfinitytv;

import android.os.Handler;
import android.os.HandlerThread;

public class SimpleTaskRunner {
    public void launchTask(final Task myTask) {
        HandlerThread thread = new HandlerThread("");
        thread.start();
        new Handler(thread.getLooper()).post(myTask);
    }
}

