package com.example.android.moviemanagment;

/**
 * Created by Android on 18/03/2018.
 */

import android.app.Application;

import android.content.Context;



public class App extends Application {



    private static Context mContext;



    public static Context getContext() {

        return mContext;

    }



    public static void setContext(Context mContext) {

        App.mContext = mContext;

    }







}