package com.example.homework3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        final String[] carsNameArray = res.getStringArray(R.array.car_classes);
        final ArrayList<CarsObject> carsObjects = new ArrayList<>();
        for (int i = 0; i < carsNameArray.length; i++) {
            String carName = carsNameArray[i];
            int resourceId =  this.getResources().getIdentifier(carName.toLowerCase(), "drawable", getPackageName());
            Drawable carImage = getResources().getDrawable(resourceId);
            CarsObject newCar = new CarsObject(carName, carImage);
            carsObjects.add(newCar);
        }

        ArrayAdapter<CarsObject> adapter = new ArrayAdapter<CarsObject>(this, 0, carsObjects) {
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                CarsObject car = carsObjects.get(position);
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.car_list, null, false);
                }

                ViewHolder viewHolder = (ViewHolder)convertView.getTag();
                if(viewHolder == null){
                    viewHolder = new ViewHolder();
                    viewHolder.imageViewCar = convertView.findViewById(R.id.car_img);
                    viewHolder.textViewClassNameCar = convertView.findViewById(R.id.car_class);

                    convertView.setTag(viewHolder);
                }

                viewHolder.imageViewCar.setImageDrawable(car.carImg);
                viewHolder.textViewClassNameCar.setText(car.carClassName);


                return convertView;
            }
        };

        GridView gridView = new GridView(this);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        setContentView(gridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                CharSequence text =
                        "Clicked position is: " + String.valueOf(position) +
                        "\n" +
                        "Class name is: " + carsObjects.get(position).carClassName;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
}
