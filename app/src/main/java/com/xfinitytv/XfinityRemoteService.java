package com.xfinitytv;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import javax.inject.Inject;

public class XfinityRemoteService extends Service {

    @Inject
    SimpleTaskRunner taskRunner;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        XfinityDependencyModule module = new XfinityDependencyModule();
        XfinityObjectGraph.initialize(module);
        XfinityObjectGraph.inject(this);
        if (intent != null) {
            handleCommand(intent);
            return Service.START_STICKY;
        } else {
            return super.onStartCommand(null, flags, startId);
        }
    }

    private void handleCommand(final Intent intent) {
        Action action = Action.valueOf(intent.getAction());
        processCommand(action, intent);
    }

    private void processCommand(Action action, Intent intent) {
        Task task = action.createTask();
        taskRunner.launchTask(task);
    }

    public enum Action {
        GET_PROGRAMS() {
            @Override
            Task createTask() {
                return new GetProgramListTask();
            }
        };
        abstract Task createTask();
    }
}
