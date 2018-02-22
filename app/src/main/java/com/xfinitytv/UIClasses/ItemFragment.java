package com.xfinitytv.UIClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xfinitytv.BusEvents.ProgramsDataEvent;
import com.xfinitytv.ModelObjects.ProgramData;
import com.xfinitytv.ProgramDataAdapter;
import com.xfinitytv.R;
import com.xfinitytv.Util.XfinityConstants;
import com.xfinitytv.XfinityObjectGraph;
import com.xfinitytv.XfinityRemoteService;
import com.xfinitytv.dataSource.DataSourceIF;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

public class ItemFragment extends AbstractFragment {

    ListView listView;

    @Inject
    DataSourceIF dataSourceIF;

    private ProgramDataAdapter mAdapter = null;
    private AlertDialog serviceErrorDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_fragment_layout, container, false);
        listView = (ListView) rootView.findViewById(R.id.programListView);
        XfinityObjectGraph.inject(this);
        EventBus.getDefault().register(this);
        initializeListViewAdapter();
        setUpListViewOnCilckListener();
        return rootView;
    }

    private void initiateServiceCallForProgramData() {
        Intent serviceIntent = new Intent(getActivity(), XfinityRemoteService.class);
        serviceIntent.setAction(XfinityRemoteService.Action.GET_PROGRAMS.name());
        getActivity().startService(serviceIntent);
    }

    private void setUpListViewOnCilckListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProgramData programData = (ProgramData) listView.getItemAtPosition(position);
                String videoPrimaryEntityId = programData.getVideoPrimaryEntityId();
                try {
                    ((OnProgramDataSelectedListener) getActivity()).reportItemSelected(videoPrimaryEntityId);
                } catch (ClassCastException e) {
                    Log.e(XfinityConstants.TAG, "Parent activity did not implement the required listener", e);
                }
            }
        });
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(ProgramsDataEvent event) {
        if (event.isTaskSuccessful()) {
            reDrawListWithLatestData();
        } else {
            displayErrorDialog();
        }
    }

    private void displayErrorDialog() {
        serviceErrorDialog = createOneButtonDialog(getActivity(), getString(R.string.unable_to_get_data_dialog_error_msg), null)
                .setNeutralButton(R.string.dialog_ok_button_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .show();
    }

    public void reDrawListWithLatestData() {
        List<ProgramData> programDataList = dataSourceIF.retrieveProgramsData();
        mAdapter.addAll(programDataList);
        mAdapter.notifyDataSetChanged();
    }

    public void initializeListViewAdapter() {
        mAdapter = new ProgramDataAdapter(getActivity(), R.layout.list_item_layout, new ArrayList<ProgramData>());
        listView.setAdapter(mAdapter);
    }

    public interface OnProgramDataSelectedListener {
        public void reportItemSelected(String listItemPrimaryId);
    }

    private void dismissErrorDialogIfExists() {
        if (serviceErrorDialog != null && serviceErrorDialog.isShowing()) {
            serviceErrorDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter.getCount() ==  0) {
            initiateServiceCallForProgramData();
        } else {
            reDrawListWithLatestData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        dismissErrorDialogIfExists();
    }
}
