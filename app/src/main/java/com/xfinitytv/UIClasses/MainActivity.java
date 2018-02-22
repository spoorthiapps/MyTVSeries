package com.xfinitytv.UIClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xfinitytv.R;
import com.xfinitytv.Util.XfinityConstants;


public class MainActivity extends Activity implements ItemFragment.OnProgramDataSelectedListener, DetailsFragment.DetailsFragmentInterface, XfinityConstants {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
    }

    @Override
    public void reportItemSelected(String listItemPrimaryId) {
        DetailsFragment displayFrag = (DetailsFragment) getFragmentManager()
                .findFragmentById(R.id.multiPaneDetails);
        if (displayFrag == null  || !displayFrag.isInLayout()) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(XfinityConstants.PROGRAM_SELECTION_EXTRA, listItemPrimaryId);
            startActivity(intent);
        } else {
            displayFrag.updateContent(listItemPrimaryId);
        }
    }

    @Override
    public String getCurrentSelectedProgramId() {
        return XfinityConstants.DEFAULT_ITEM_SELECTION;
    }
}
