package com.xfinity.sdk.serializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.xfinity.sdk.response.ProgramsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.xfinity.sdk.response.ProgramsResponse.Item;
import static com.xfinity.sdk.response.ProgramsResponse.VideoGalleries;

public class CustomDeserializer implements JsonDeserializer<VideoGalleries> {

    private final String JSON_KEY_ITEMS = "items";

    public CustomDeserializer() {
        Logger.getLogger("").log(Level.WARNING, "");
    }

    @Override
    public VideoGalleries deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        VideoGalleries videoGalleries = new VideoGalleries();
        videoGalleries.galleryItem = new ProgramsResponse.GalleryItem();
        JsonObject responseJson = jsonElement.getAsJsonObject();
        List<Item> extractedItemsList = new ArrayList<Item>();
        if (responseJson.has(JSON_KEY_ITEMS)) {
            JsonElement element = responseJson.get(JSON_KEY_ITEMS);
            if (element.isJsonNull() || element.isJsonPrimitive()) {
                // should return empty list
            } else if (element.isJsonArray()) {
                extractVideoItemList(context, extractedItemsList, element);
            }
            videoGalleries.galleryItem.items = extractedItemsList;
        }
        return videoGalleries;
    }

    private void extractVideoItemList(JsonDeserializationContext context, List<Item> extractedItemsList, JsonElement element) {
        for (JsonElement elementInArray : element.getAsJsonArray()) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(elementInArray);
            Item item =  gson.fromJson(jsonString, Item.class);
            extractedItemsList.add(item);
        }
    }
}
