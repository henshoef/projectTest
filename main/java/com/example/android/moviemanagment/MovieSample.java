package com.example.android.moviemanagment;

import android.content.Context;


/**
 * Created by Android on 11/03/2018.
 */

public class MovieSample{
     private  int id;
    private  String name;
    private  String decription;
    private  String imageUrl;
    private int no;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    public  MovieSample(int id, String name , String decription , String imageUrl){
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.imageUrl = imageUrl;
    }

    public  MovieSample(String name , String decription , String imageUrl){
        this.name = name;
        this.decription = decription;
        this.imageUrl = imageUrl;
    }

    public  MovieSample(String name , String decription){
        this.name = name;
        this.decription = decription;

    }

    public  MovieSample(String name ){

        this.name = name;

    }
    public  MovieSample(int id, String name , String decription , String imageUrl,int no){
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.imageUrl = imageUrl;
        this.no = no;
    }

    public  MovieSample(String name , String decription , String imageUrl,int no){
        this.name = name;
        this.decription = decription;
        this.imageUrl = imageUrl;
        this.no = no;
    }

    public  MovieSample(String name , String decription,int no){
        this.name = name;
        this.decription = decription;
        this.no = no;
    }

    public  MovieSample(String name,int no ) {

        this.name = name;
        this.no = no;
    }
    public MovieSample() {

    }
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void set(MovieSample m){
        name =m.getName();
        decription = m.getDecription();
    }
}
