package lv.denisskaibagarovs.homework5.PhotoItemsPresenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import lv.denisskaibagarovs.homework5.Interfaces.PhotoItem;
import lv.denisskaibagarovs.homework5.R;
import lv.denisskaibagarovs.homework5.ViewHolder;

public class PhotoItemsAdapter extends ArrayAdapter {

    PhotoItem[] photoItems;

    public PhotoItemsAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
        this.photoItems = (PhotoItem[]) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PhotoItem photoItem = photoItems[position];
        // Inflate only once
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_item_img, null, false);
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
}
