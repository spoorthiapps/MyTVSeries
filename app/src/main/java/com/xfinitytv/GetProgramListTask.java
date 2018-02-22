package com.xfinitytv;

import com.xfinity.sdk.ProgramDataListener.BaseResponseListener;
import com.xfinity.sdk.api.XfinitySDKIF;
import com.xfinity.sdk.enums.APITYPE;
import com.xfinity.sdk.factory.XfinitySDKFactory;
import com.xfinity.sdk.response.BaseResponse;
import com.xfinitytv.BusEvents.ProgramsDataEvent;
import com.xfinitytv.ModelObjects.ProgramData;
import com.xfinitytv.dataSource.DataSourceIF;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class GetProgramListTask implements Task, BaseResponseListener {

    @Inject
    XfinitySDKFactory sdkFactory;

    @Inject
    DataSourceIF dataSource;

    public GetProgramListTask() {
        XfinityObjectGraph.inject(this);
    }

    @Override
    public void run() {
        XfinitySDKIF xfinitySDK = sdkFactory.createXfinitySDK(APITYPE.LIVE);
        xfinitySDK.getXfinityProgramData(this);
    }

    @Override
    public void onSuccess(BaseResponse response) {
        XfinityProgramDataToModelConvertor convertor = new XfinityProgramDataToModelConvertor(response);
        List<ProgramData> programDataList = convertor.buildModelObjectFromResponse();
        dataSource.deleteAllTheRecordsFromProgramData();
        dataSource.saveProgramsDataToDatabase(programDataList);
        EventBus.getDefault().post(new ProgramsDataEvent(true));
    }

    @Override
    public void onFailure() {
        EventBus.getDefault().post(new ProgramsDataEvent(false));
    }
}
