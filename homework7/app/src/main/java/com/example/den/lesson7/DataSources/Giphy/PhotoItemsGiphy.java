package com.example.den.lesson7.DataSources.Giphy;

import com.example.den.lesson7.Interfaces.PhotoItem;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

@Table
public class PhotoItemsGiphy implements PhotoItem  {

    // that the main pain
    // because DB need to have its own ID, it conflict with ID that we receive from server
    // In order to overcome that, we need to manually resolve them
    @SerializedName("db_id") // ID that use local DB
    private transient Long id;

    @SerializedName("id") // ID that came from server
    private String imgID;

    ImagesContainer images;
    String username;
    boolean isDeleted = false;

    private String imagesForORM;

    public PhotoItemsGiphy() { }

    @Override
    public String getID() {return this.imgID;}

    @Override
    public boolean getIsDeleted() {return this.isDeleted;}

    @Override
    public String getImgUrl() {
        if(images == null) { // Because this DB does not know how to fully recreate object, we need to re-create its nested properties back manually
            this.images = new Gson().fromJson(this.imagesForORM, ImagesContainer.class);
        }

        return images.getMediumSize();
    }

    @Override
    public String getAuthorName() {
        return username;
    }

    @Override
    public void saveToDatabase() {
        if(isSavedRecordExist()) { // if exist -> update
            update(false);
        } else {
            // before save - convert inner objects to string
            this.imagesForORM = images.toString();

            SugarRecord.save(this);
        }
    }

    @Override
    public void deleteFromDatabase() {
        // A small query is needed because of conflicted id properties
        SugarRecord.deleteAll(PhotoItemsGiphy.class,"img_ID = ?", this.imgID);
    }

    @Override
    public void deleteFromDatabaseSafe() {
        update(true);
    }

    @Override
    public boolean isSavedToDatabase() {
        PhotoItemsGiphy item = getSavedRecord();
        if (item == null) {
            return false; // if no record exist -> not saved
        }
        return !item.isDeleted; // if record exist -> return its value
    }

    private void update(boolean isDeleted) {
        this.isDeleted = isDeleted;
        SugarRecord.executeQuery("UPDATE PHOTO_ITEMS_GIPHY SET IS_DELETED = ? WHERE img_ID = ?", this.isDeleted ? "1" : "0", this.imgID);
    }

    private boolean isSavedRecordExist() {
        return getSavedRecord() != null;
    }

    private PhotoItemsGiphy getSavedRecord() {
        List<PhotoItemsGiphy> items = SugarRecord.find(PhotoItemsGiphy.class,"img_ID = ?", this.imgID);
        if(items.isEmpty()) { return null; } // If empty -> nothing to return
        return items.get(0);
    }

    public class ImagesContainer implements Serializable {

        DownsizedMedium downsized_medium;
        String getMediumSize() {
            return downsized_medium.url;
        }

        public String toString() {
            // convert to a String object, because it could be saved to database without much hassle
            return new Gson().toJson(this);
        }
    }

    public class DownsizedMedium implements Serializable {
        String url;
    }
}
