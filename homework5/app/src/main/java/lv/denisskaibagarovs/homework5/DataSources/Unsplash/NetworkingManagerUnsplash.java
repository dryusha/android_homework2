package lv.denisskaibagarovs.homework5.DataSources.Unsplash;

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

public class NetworkingManagerUnsplash implements NetworkingManager {


    @Override
    public void getImages(NetworkingManagerResult resultCallback) {
        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        JsonParser parser = new JsonParser();
        final ArrayList<PhotoItemUnsplash> photoItemUnsplashes = new ArrayList<>();

        Request request = new Request.Builder()
                .url("https://api.unsplash.com/photos/?client_id=311ed690d7678d20b8ce556e56d5bf168d6ddf9fa1126e58193d95089d796542")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    JSONArray array = new JSONArray(jsonData);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject imgObject = array.getJSONObject(i);

                        JsonElement mJson = parser.parse(imgObject.toString());
                        //TODO 3 uisng GSON parse mJson to PhotoItemUnsplash object
                        PhotoItemUnsplash photoItemUnsplash = gson.fromJson(mJson, PhotoItemUnsplash.class);
                        photoItemUnsplashes.add(photoItemUnsplash);

                    }

                    resultCallback.callback(photoItemUnsplashes.toArray(new PhotoItemUnsplash[photoItemUnsplashes.size()]));


                } catch (JSONException ex) {
                    Log.e("JSON-PARSE",ex.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {

            }});

    }

}
