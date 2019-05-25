package com.example.den.lesson7.DataSources.Local;

import com.example.den.lesson7.DataSources.Giphy.PhotoItemsGiphy;
import com.example.den.lesson7.DataSources.Unsplash.PhotoItemUnsplash;
import com.example.den.lesson7.Interfaces.NetworkingManager;
import com.example.den.lesson7.Interfaces.NetworkingResultListener;
import com.example.den.lesson7.Interfaces.PhotoItem;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class NetworkingManagerLocal implements NetworkingManager {
    @Override
    public void getPhotoItems(NetworkingResultListener result) {
        result.callback(getAllSavedPhotoItems());
    }

    @Override
    public void fetchNewItemsFromPosition(int lastPosition, NetworkingResultListener result) {

    }

    private void removeElementIfItDeleted(List<PhotoItem> allFavoritedItems){
        for(int i = allFavoritedItems.size()-1 ; i >= 0; i--){
            if (allFavoritedItems.get(i).getIsDeleted()){
                allFavoritedItems.remove(i);
            }
        }
    }

    private PhotoItem[] getAllSavedPhotoItems() {

        List<PhotoItem> allFavoritedItems = new ArrayList<PhotoItem>();

        allFavoritedItems.addAll(SugarRecord.listAll(PhotoItemsGiphy.class));
        allFavoritedItems.addAll(SugarRecord.listAll(PhotoItemUnsplash.class));

        removeElementIfItDeleted(allFavoritedItems);

        return allFavoritedItems.toArray(new PhotoItem[allFavoritedItems.size()]);
    }
}
