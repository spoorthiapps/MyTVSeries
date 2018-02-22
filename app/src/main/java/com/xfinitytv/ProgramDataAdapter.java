package com.xfinitytv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xfinitytv.ModelObjects.ProgramData;

import java.util.ArrayList;
import java.util.List;

public class ProgramDataAdapter extends ArrayAdapter<ProgramData> {

    Context context;
    int layoutResourceId;
    List<ProgramData> programDataList = new ArrayList<ProgramData>();

    public ProgramDataAdapter(Context context, int layoutResourceId, List<ProgramData> programDataList) {
        super(context, layoutResourceId, programDataList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.programDataList = programDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(context);
            row = layoutInflator.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) row.findViewById(R.id.programImage);
            holder.programName = (TextView) row.findViewById(R.id.programName);
            holder.videoName = (TextView) row.findViewById(R.id.videoName);
            holder.episodeNumber = (TextView) row.findViewById(R.id.episodeNumber);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        ProgramData currentProgramData = programDataList.get(position);
        Picasso.with(context).load(currentProgramData.entityThumbnailUrl).into(holder.imageView);
        holder.programName.setText("Series Name: " + currentProgramData.getName());
        holder.videoName.setText("Video Name: "+ currentProgramData.getVideoName());
        holder.episodeNumber.setText("Episode Number: " + currentProgramData.getEpisodeNumber());
        return row;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView programName;
        TextView videoName;
        TextView episodeNumber;
    }
}
