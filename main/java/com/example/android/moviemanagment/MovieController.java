package com.example.android.moviemanagment;

/**
 * Created by Android on 18/03/2018.
 */


import android.app.Activity;

import android.app.ProgressDialog;

import android.widget.ListView;

import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;





public abstract class MovieController  implements HttpRequest.Callbacks {



    protected static ArrayList<String> Movies;

    protected Activity activity;

    protected ProgressDialog progressDialog;

    protected ListView listViewMovies;

    public List<MovieSample> allMoviesData;





    public MovieController(Activity activity) {

        this.activity = activity;

        listViewMovies = (ListView)activity.findViewById(R.id.movieList);

        progressDialog = new ProgressDialog(activity);

        progressDialog.setTitle("Downloading...");

        progressDialog.setMessage("Please Wait...");

    }





    public void onAboutToStart() {

        progressDialog.show();

    }





    public void onError(String errorMessage) {

        Toast.makeText(activity, "Error: " + errorMessage, Toast.LENGTH_LONG).show();

        progressDialog.dismiss();

    }

}