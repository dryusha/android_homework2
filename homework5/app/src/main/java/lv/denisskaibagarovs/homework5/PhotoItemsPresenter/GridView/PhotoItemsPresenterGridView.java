package lv.denisskaibagarovs.homework5.PhotoItemsPresenter.GridView;

import android.app.Activity;
import android.widget.GridView;

import lv.denisskaibagarovs.homework5.Interfaces.PhotoItemsPresenter;
import lv.denisskaibagarovs.homework5.Interfaces.PhotoItem;
import lv.denisskaibagarovs.homework5.PhotoItemsPresenter.PhotoItemsAdapter;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {
    @Override
    public void showPhotoItems(Activity activity, PhotoItem[] photoItems) {
        GridView gridView = new GridView(activity);
        activity.setContentView(gridView);
        gridView.setNumColumns(2);
        gridView.setColumnWidth(40);
        gridView.setVerticalSpacing(20);
        gridView.setHorizontalSpacing(20);
        gridView.setAdapter(new PhotoItemsAdapter(activity,0, photoItems));
    }
}
