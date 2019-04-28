package lv.denisskaibagarovs.homework5.PhotoItemsPresenter.ListView;

import android.app.Activity;
import android.widget.ListView;

import lv.denisskaibagarovs.homework5.Interfaces.PhotoItem;
import lv.denisskaibagarovs.homework5.Interfaces.PhotoItemsPresenter;
import lv.denisskaibagarovs.homework5.PhotoItemsPresenter.PhotoItemsAdapter;

public class PhotoItemsPresenterListView implements PhotoItemsPresenter {
    @Override
    public void showPhotoItems(Activity activity, PhotoItem[] photoItems) {

        ListView listView = new ListView(activity);
        activity.setContentView(listView);
        listView.setAdapter(new PhotoItemsAdapter(activity,0, photoItems));
    }
}
