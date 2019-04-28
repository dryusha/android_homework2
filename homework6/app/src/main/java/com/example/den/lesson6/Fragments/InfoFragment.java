package com.example.den.lesson6.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.den.lesson6.Interfaces.PhotoItem;
import com.example.den.lesson6.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    public interface InfoFragmentListener {
        void onBackPress();
    }

    private InfoFragmentListener listener;
    public PhotoItem photoItem;
    @BindView(R.id.imageViewImage) ImageView imageViewImage;
    @BindView(R.id.authorNameText) TextView authorNameText;
    @BindView(R.id.buttonBack) Button buttonBack;

    public InfoFragment() {}

    // Method that would connect callbacks of
    // Fragment to activity, no need to do that
    // Inside activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoFragmentListener) {
            listener = (InfoFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ShareFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);

        // Load images
        Picasso.get().load(photoItem.getImgUrl()).into(imageViewImage);
        authorNameText.setText(photoItem.getAuthorName());

        buttonBack.setOnClickListener(button -> {
            listener.onBackPress();
        });


        return view;
    }

}
