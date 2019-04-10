package lv.denisskaibagarovs.homework5.DataSources.Unsplash;

import java.util.Map;

import lv.denisskaibagarovs.homework5.Interfaces.PhotoItem;

public class PhotoItemUnsplash implements PhotoItem {
    Map<String,String> urls;
    User user;

    public String getImgUrl() {
        return this.urls.get("regular");
    }

    @Override
    public String getUserName() {
        return this.user.name;
    }

    // TODO Write a function to get authorName from User

    public class User {
        String name;
        String location;
    }

    @Override
    public String getLocation(){
        return this.user.location;
    }
}
