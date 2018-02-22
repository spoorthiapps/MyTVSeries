package com.xfinitytv.dataSource;

import com.xfinitytv.ModelObjects.ProgramData;

import java.util.List;

public interface DataSourceIF {
    public void saveProgramsDataToDatabase(List<ProgramData> programData);
    public void deleteAllTheRecordsFromProgramData();
    public List<ProgramData> retrieveProgramsData();
    public ProgramData retrieveSingleProgramDataByKey(String videoPrimaryId);
}
