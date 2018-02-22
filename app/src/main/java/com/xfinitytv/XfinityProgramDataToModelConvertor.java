package com.xfinitytv;

import com.xfinity.sdk.response.BaseResponse;
import com.xfinity.sdk.response.ProgramsResponse;
import com.xfinitytv.ModelObjects.ProgramData;

import java.util.ArrayList;
import java.util.List;

import static com.xfinity.sdk.response.ProgramsResponse.GalleryItem;
import static com.xfinity.sdk.response.ProgramsResponse.Item;
import static com.xfinity.sdk.response.ProgramsResponse.VideoGalleries;

public class XfinityProgramDataToModelConvertor {

    BaseResponse baseResponse;

    public XfinityProgramDataToModelConvertor(BaseResponse response) {
        this.baseResponse = response;
    }

    public List<ProgramData> buildModelObjectFromResponse() {
        ProgramsResponse programsResponse = (ProgramsResponse) baseResponse;
        List<ProgramData> programDataList = new ArrayList<ProgramData>();
        for (VideoGalleries gallery : programsResponse.videoGalleries) {
            GalleryItem galleryItem = gallery.galleryItem;
            if (galleryItem != null) {
                for (Item item : galleryItem.items) {
                    ProgramData programData = new ProgramData();
                    programData.setEntityEpisodeCount(item.entityEpisodeCount);
                    programData.setEntityPosterArtUrl(item.entityPosterArtUrl);
                    programData.setEntityThumbnailUrl(item.entityThumbnailUrl);
                    programData.setEntityType(item.entityType);
                    programData.setEpisodeName(item.episodeName);
                    programData.setEpisodeOriginalAirDate(item.episodeOriginalAirDate);
                    programData.setIsAdult(item.isAdult);
                    programData.setEpisodeNumber(item.episodeNumber);
                    programData.setLink(item.link);
                    programData.setNetworkLogoUrl(item.networkLogoUrl);
                    programData.setVideoNetworkDisplayName(item.videoNetworkDisplayName);
                    programData.setName(item.name);
                    programData.setVideoPrimaryEntityId(item.videoPrimaryEntityId);
                    programData.setEntityThumbnailUrl(item.entityThumbnailUrl);
                    programData.setVideoDescription(item.videoDescription);
                    programData.setVideoDuration(item.videoDuration);
                    programData.setVideoAirDate(item.videoAirDate);
                    programData.setVideoExpirationDate(item.videoExpirationDate);
                    programData.setVideoHasExpired(item.videoHasExpired);
                    programData.setVideoType(item.videoType);
                    programData.setNetworkLogoUrl(item.networkLogoUrl);
                    programData.setVideoRating(item.videoRating);
                    programData.setVideoDescription(item.videoDescription);
                    programData.setVideoName(item.videoName);
                    programDataList.add(programData);
                }
            }
        }
        return programDataList;
    }
}
