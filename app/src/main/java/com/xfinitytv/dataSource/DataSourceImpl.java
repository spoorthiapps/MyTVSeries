package com.xfinitytv.dataSource;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.util.Log;
import com.xfinitytv.ModelObjects.ProgramData;
import com.xfinitytv.Util.XfinityConstants;

import java.util.List;

public class DataSourceImpl implements DataSourceIF, XfinityConstants {
    @Override
    public void saveProgramsDataToDatabase(List<ProgramData> programData) {
        ActiveAndroid.beginTransaction();
        try {
            for (ProgramData data : programData) {
                data.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    @Override
    public List<ProgramData> retrieveProgramsData() {
        return new Select().from(ProgramData.class).execute();
    }

    @Override
    public ProgramData retrieveSingleProgramDataByKey(String videoPrimaryId) {
        try {
            return new Select()
                    .from(ProgramData.class)
                    .where("videoPrimaryEntityId = ?", videoPrimaryId).executeSingle();
        } catch (Exception e) {
            Log.e(XfinityConstants.TAG, "Error while retrieving the data", e);
            return null;
        }
    }

    @Override
    public void deleteAllTheRecordsFromProgramData() {
        new Delete().from(ProgramData.class).execute();
    }
}
