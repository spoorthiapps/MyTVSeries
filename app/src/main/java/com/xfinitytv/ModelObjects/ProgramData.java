package com.xfinitytv.ModelObjects;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "ProgramData")
public class ProgramData extends Model {
    @Column(unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String videoPrimaryEntityId;
    @Column
    public String entityThumbnailUrl;
    @Column
    public String link;
    @Column
    public String videoExpirationDate;
    @Column
    public String entityEpisodeCount;
    @Column
    public String videoName;
    @Column
    public String episodeNumber;
    @Column
    public String entityType;
    @Column
    public String videoNetworkDisplayName;
    @Column
    public String videoHasExpired;
    @Column
    public String videoAirDate;
    @Column
    public String episodeName;
    @Column
    public String name;
    @Column
    public String videoDescription;
    @Column
    public boolean isAdult;
    @Column
    public String videoRating;
    @Column
    public String episodeOriginalAirDate;
    @Column
    public String videoType;
    @Column
    public String entityPosterArtUrl;
    @Column
    public String videoDuration;
    @Column
    public String networkLogoUrl;
    public String getNetworkLogoUrl() {
        return networkLogoUrl;
    }

    public void setNetworkLogoUrl(String networkLogoUrl) {
        this.networkLogoUrl = networkLogoUrl;
    }

    public String getEntityThumbnailUrl() {
        return entityThumbnailUrl;
    }

    public void setEntityThumbnailUrl(String entityThumbnailUrl) {
        this.entityThumbnailUrl = entityThumbnailUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVideoExpirationDate() {
        return videoExpirationDate;
    }

    public void setVideoExpirationDate(String videoExpirationDate) {
        this.videoExpirationDate = videoExpirationDate;
    }

    public String getEntityEpisodeCount() {
        return entityEpisodeCount;
    }

    public void setEntityEpisodeCount(String entityEpisodeCount) {
        this.entityEpisodeCount = entityEpisodeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public String getVideoPrimaryEntityId() {
        return videoPrimaryEntityId;
    }

    public void setVideoPrimaryEntityId(String videoPrimaryEntityId) {
        this.videoPrimaryEntityId = videoPrimaryEntityId;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getVideoNetworkDisplayName() {
        return videoNetworkDisplayName;
    }

    public void setVideoNetworkDisplayName(String videoNetworkDisplayName) {
        this.videoNetworkDisplayName = videoNetworkDisplayName;
    }

    public String getVideoHasExpired() {
        return videoHasExpired;
    }

    public void setVideoHasExpired(String videoHasExpired) {
        this.videoHasExpired = videoHasExpired;
    }

    public String getVideoAirDate() {
        return videoAirDate;
    }

    public void setVideoAirDate(String videoAirDate) {
        this.videoAirDate = videoAirDate;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getVideoRating() {
        return videoRating;
    }

    public void setVideoRating(String videoRating) {
        this.videoRating = videoRating;
    }

    public String getEpisodeOriginalAirDate() {
        return episodeOriginalAirDate;
    }

    public void setEpisodeOriginalAirDate(String episodeOriginalAirDate) {
        this.episodeOriginalAirDate = episodeOriginalAirDate;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getEntityPosterArtUrl() {
        return entityPosterArtUrl;
    }

    public void setEntityPosterArtUrl(String entityPosterArtUrl) {
        this.entityPosterArtUrl = entityPosterArtUrl;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }
}
