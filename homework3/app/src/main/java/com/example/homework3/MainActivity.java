package com.example.homework3;

import android.content.Context;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        final CarsObject[] carsObjects = {
            new CarsObject("convertible"),
            new CarsObject("coupe"),
            new CarsObject("crossover"),
            new CarsObject("sedan"),
            new CarsObject("truck"),
            new CarsObject("van"),
            new CarsObject("wagon")
        };

        ArrayAdapter<CarsObject> adapter = new ArrayAdapter<CarsObject>(this, 0, carsObjects) {
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                CarsObject car = carsObjects[position];
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

                int carImgId = getResources().getIdentifier(car.className, "drawable", getPackageName());
                viewHolder.imageViewCar.setImageResource(carImgId);
                viewHolder.textViewClassNameCar.setText(car.className);


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
                        "Class name is: " + carsObjects[position].className;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

    }
}
