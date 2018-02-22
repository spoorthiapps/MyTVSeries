package com.xfinity.sdk.response;

import java.util.List;

public class ProgramsResponse extends BaseResponse {
    public Footer footer;
    public List<VideoGalleries> videoGalleries;

    public static class Footer {
        public String name;
    }

    public static class VideoGalleries {
        public GalleryItem galleryItem;
    }

    public static class GalleryItem {
        public List<Item> items;
        public Header header;
    }

    public static class Item {
        public String entityThumbnailUrl;
        public String link;
        public String videoExpirationDate;
        public String entityEpisodeCount;
        public String videoName;
        public String name;
        public String episodeNumber;
        public String videoPrimaryEntityId;
        public String entityType;
        public String videoNetworkDisplayName;
        public String videoHasExpired;
        public String videoAirDate;
        public String episodeName;
        public String videoDescription;
        public boolean isAdult;
        public String videoRating;
        public String episodeOriginalAirDate;
        public String videoType;
        public String entityPosterArtUrl;
        public String videoDuration;
        public String networkLogoUrl;
    }

    public static class Header {
        public String name;
        public String description;
    }
}
