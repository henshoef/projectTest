package com.example.android.moviemanagment;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 18/03/2018.
 */


public class MoviesReaderController extends MovieController {
    public Activity contex;

    public List<MovieSample> giveMovies(){
        return allMoviesData;
    }

    public MoviesReaderController(Activity activity) {
        super(activity);
        contex =activity;
    }


    public void readAllMovies(String name) {
        HttpRequest httpRequest = new HttpRequest(this);
        httpRequest.execute("https://api.themoviedb.org/3/search/movie?api_key=0dfa979f5f5b49d638840ce5b53339c1&query="+name+"&page=1");
    }


    public void onSuccess(String downloadedText) {

        try {
            List<MovieSample> tempList = new ArrayList<>();

            JSONObject jsonArray = new JSONObject(downloadedText);

            JSONArray resultArray = jsonArray.getJSONArray("results");



            Movies = new ArrayList<>();


            for (int i = 0; i < resultArray.length(); i++) {


                JSONObject jsonObject = resultArray.getJSONObject(i);
                int No = jsonObject.getInt("id");
                String name = jsonObject.getString("title");
                String desc = jsonObject.getString("overview");
                String image = jsonObject.getString("poster_path");

                MovieSample movie = new MovieSample(name,desc,image,No);
                tempList.add(i,movie);



                Movies.add(name);
            }

            allMoviesData = tempList;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, Movies);


            listViewMovies.setAdapter(adapter);

        }
        catch (JSONException ex) {
            Toast.makeText(activity, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }


        progressDialog.dismiss();


    }
}