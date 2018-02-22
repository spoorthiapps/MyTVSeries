package com.xfinitytv.UIClasses;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinitytv.ModelObjects.ProgramData;
import com.xfinitytv.R;
import com.xfinitytv.Util.XfinityConstants;
import com.xfinitytv.XfinityObjectGraph;
import com.xfinitytv.dataSource.DataSourceIF;

import java.util.List;

import javax.inject.Inject;

public class DetailsFragment extends Fragment implements XfinityConstants {

    @Inject
    DataSourceIF dataSourceIF;

    private ImageView programImage, networkLogo;
    private TextView programName, episodeNumber, videoDescription,
            supervisionLabel, videoAirTime, videoRating, videoType;
    private Button movieLaunchButton;

    private ProgramData currentProgramData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        XfinityObjectGraph.inject(this);
        View rootView = inflater.inflate(R.layout.details_fragment_layout, container, false);
        instantiateViewComponents(rootView);
        setLaunchOnClickListener();
        return rootView;
    }

    public void setCurrentProgramData(ProgramData currentProgramData) {
        this.currentProgramData = currentProgramData;
    }

    private String extractPrimaryKeyOfFirstDataItem() {
        String videoPrimaryId = null;
        List<ProgramData> programDataList = dataSourceIF.retrieveProgramsData();
        if (programDataList != null && programDataList.size() > 0) {
            videoPrimaryId = programDataList.get(0).getVideoPrimaryEntityId();
        }
        return videoPrimaryId;
    }

    private ProgramData extractProgramDataBasedOnKey(String selectedKey) {
       return dataSourceIF.retrieveSingleProgramDataByKey(selectedKey);
    }

    private void populateViews() {
        if (currentProgramData != null) {
            programName.setText(currentProgramData.getName());
            episodeNumber.setText(getString(R.string.episode_label) + currentProgramData.getEpisodeNumber());
            videoDescription.setText(currentProgramData.getVideoDescription());
            supervisionLabel.setText(parentSupervisionString(currentProgramData.isAdult));
            videoAirTime.setText(currentProgramData.getVideoAirDate());
            videoRating.setText(currentProgramData.getVideoRating());
            videoType.setText(currentProgramData.getVideoType());
            Picasso.with(getActivity()).load(currentProgramData.entityThumbnailUrl).into(programImage);
            Picasso.with(getActivity()).load(currentProgramData.networkLogoUrl).into(networkLogo);
        }
    }

    private String parentSupervisionString(boolean isAdult) {
        return isAdult ? getString(R.string.yes_label) : getString(R.string.no_label);
    }

    private void instantiateViewComponents(View rootView) {
        programName = (TextView) rootView.findViewById(R.id.programName);
        episodeNumber = (TextView) rootView.findViewById(R.id.episodeNumber);
        videoDescription = (TextView) rootView.findViewById(R.id.videoDescription);
        supervisionLabel = (TextView) rootView.findViewById(R.id.supervisionLabel);
        videoAirTime = (TextView) rootView.findViewById(R.id.videoAirTime);
        videoRating = (TextView) rootView.findViewById(R.id.videoRating);
        videoType = (TextView) rootView.findViewById(R.id.videoType);
        programImage = (ImageView) rootView.findViewById(R.id.programImage);
        networkLogo = (ImageView) rootView.findViewById(R.id.networklogo);
        movieLaunchButton = (Button) rootView.findViewById(R.id.movieLaunchButton);
    }

    private void setLaunchOnClickListener() {
        movieLaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentProgramData.getLink()));
                startActivity(browserIntent);
            }
        });
    }

    public void updateContent(String selectedItemKey) {
        setCurrentProgramData(extractProgramDataBasedOnKey(selectedItemKey));
        populateViews();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String currentSelectedProgramId = ((DetailsFragmentInterface) getActivity()).getCurrentSelectedProgramId();
        if (currentSelectedProgramId == XfinityConstants.DEFAULT_ITEM_SELECTION) {
            currentSelectedProgramId = extractPrimaryKeyOfFirstDataItem();
        }
        updateContent(currentSelectedProgramId);
    }

    public interface DetailsFragmentInterface {
        public String getCurrentSelectedProgramId();
    }
}
