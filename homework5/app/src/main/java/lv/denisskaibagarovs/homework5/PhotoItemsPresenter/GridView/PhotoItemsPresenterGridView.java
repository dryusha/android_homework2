package lv.denisskaibagarovs.homework5.PhotoItemsPresenter.GridView;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.squareup.picasso.Picasso;

import lv.denisskaibagarovs.homework5.Interfaces.PhotoItemsPresenter;
import lv.denisskaibagarovs.homework5.Interfaces.PhotoItem;
import lv.denisskaibagarovs.homework5.R;
import lv.denisskaibagarovs.homework5.ViewHolder;

public class PhotoItemsPresenterGridView implements PhotoItemsPresenter {
    @Override
    public void showPhotoItems(Activity activity, PhotoItem[] photoItems) {
        ArrayAdapter<PhotoItem> adapter =
                new ArrayAdapter<PhotoItem>(activity, 0, photoItems) {
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {
                        PhotoItem photoItem = photoItems[position];
                        // Inflate only once
                        if (convertView == null) {
                            convertView = activity.getLayoutInflater()
                                    .inflate(R.layout.custom_item_img, null, false);
                        }

                        ViewHolder viewHolder;
                        if(convertView.getTag() == null) {
                            // TODO 4 Map ViewHolder elements via Butterknife
                            viewHolder = new ViewHolder(convertView);
                            convertView.setTag(viewHolder);
                        } else  {
                            viewHolder = (ViewHolder)convertView.getTag();
                        }

                        viewHolder.textViewAuthor.setText(photoItem.getUserName());
                        viewHolder.textViewLocation.setText(photoItem.getLocation());

                        Picasso.get().load(photoItem.getImgUrl()).into(viewHolder.imageView);
                        return convertView;
                    }
                };

        GridView gridView = new GridView(activity);
        activity.setContentView(gridView);
        gridView.setNumColumns(2);
        gridView.setColumnWidth(40);
        gridView.setVerticalSpacing(20);
        gridView.setHorizontalSpacing(20);
        gridView.setAdapter(adapter);

    }
}
