package lv.denisskaibagarovs.homework5;

import android.app.Activity;
import android.os.Bundle;

import lv.denisskaibagarovs.homework5.DataSources.Giphy.NetworkingManagerGiphy;
import lv.denisskaibagarovs.homework5.DataSources.Unsplash.NetworkingManagerUnsplash;
import lv.denisskaibagarovs.homework5.Interfaces.NetworkingManager;
import lv.denisskaibagarovs.homework5.Interfaces.PhotoItemsPresenter;
import lv.denisskaibagarovs.homework5.PhotoItemsPresenter.GridView.PhotoItemsPresenterGridView;
import lv.denisskaibagarovs.homework5.PhotoItemsPresenter.ListView.PhotoItemsPresenterListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NetworkingManager manager = new NetworkingManagerGiphy();
        //NetworkingManager manager = new NetworkingManagerUnsplash();
        //PhotoItemsPresenter presenter = new PhotoItemsPresenterGridView();
        PhotoItemsPresenter presenter = new PhotoItemsPresenterListView();

        manager.getImages(photoItems -> {
            runOnUiThread(()->{
                presenter.showPhotoItems(this, photoItems);
            });
        });

        // TODO 1 - Create a new NetworkingManagerGiphy that implements NetworkingManager with url https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit=25&rating=G
        // TODO 2 - Create a new PhotoItemsGiphy that implements PhotoItems
        // TODO 3 - Parse Giphy response to PhotoItemsGiphy. Note, the url for img is inside images->downsized_medium->url
        // TODO 4* - Create a new PhotoItemsPresenterListView that implement PhotoItemsPresenter and shows images in ListView
        // TODO 5 - Show Giphy images in App :)
    }
}
