package lv.denisskaibagarovs.homework5.DataSources.Giphy;

import java.util.Map;

import lv.denisskaibagarovs.homework5.Interfaces.PhotoItem;

public class PhotoItemGiphy implements PhotoItem {
    String title;
    String username;
    Images images;

    public class Images {
        Map<String,String> downsized_medium;
    }

    public String getImgUrl() {
        return this.images.downsized_medium.get("url");
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getLocation(){
        return this.title;
    }




}
