package lv.denisskaibagarovs.lesson4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GoogleMap extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);

        Intent intent = getIntent();
        String value = intent.getStringExtra("key");

        TextView textView = findViewById(R.id.textViewGoogleMap);
        textView.setText(value);
    }
}
