package com.xfinitytv.UIClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xfinitytv.R;
import com.xfinitytv.Util.XfinityConstants;

public class DetailsActivity extends Activity implements DetailsFragment.DetailsFragmentInterface, XfinityConstants {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity_layout);
    }

    @Override
    public String getCurrentSelectedProgramId() {
        Intent intent = getIntent();
        return intent.getStringExtra(XfinityConstants.PROGRAM_SELECTION_EXTRA);
    }
}
