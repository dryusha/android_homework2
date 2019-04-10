package lv.denisskaibagarovs.homework5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder {
    public @BindView(R.id.imageView) ImageView imageView;
    public @BindView(R.id.textViewAuthor) TextView textViewAuthor;
    public @BindView(R.id.textViewLocation) TextView textViewLocation;


    public ViewHolder (View view) { ButterKnife.bind(this, view); }
}
