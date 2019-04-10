package lv.denisskaibagarovs.homework5.DataSources.Giphy;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import lv.denisskaibagarovs.homework5.Interfaces.NetworkingManager;
import lv.denisskaibagarovs.homework5.Interfaces.NetworkingManagerResult;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkingManagerGiphy implements NetworkingManager {


    @Override
    public void getImages(NetworkingManagerResult resultCallback) {
        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();
        final ArrayList<PhotoItemGiphy> photoItemGiphies = new ArrayList<>();

        Request request = new Request.Builder()
                .url("https://api.giphy.com/v1/stickers/trending?api_key=VvyONhZ6eUFDFtuwg7w9tUYXzgefYdYy&limit=25&rating=G")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    //String jsonData = response.body().string();
                    //JSONArray array = new JSONArray(jsonData);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray array = jsonObject.getJSONArray("data");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);

                        JsonElement mJson = parser.parse(imgObject.toString());
                        //TODO 3 uisng GSON parse mJson to PhotoItemUnsplash object
                        PhotoItemGiphy photoItemGiphy = gson.fromJson(mJson, PhotoItemGiphy.class);
                        photoItemGiphies.add(photoItemGiphy);

                    }

                    resultCallback.callback(photoItemGiphies.toArray(new PhotoItemGiphy[photoItemGiphies.size()]));


                } catch (JSONException ex) {
                    Log.e("JSON-PARSE",ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }});

    }

}
