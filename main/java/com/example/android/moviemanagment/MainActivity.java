package com.example.android.moviemanagment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DataBaseHandler db;
    int i;
    LinearLayout l;
    boolean next;
    ArrayList<MovieSample> names;
    String tempTitle;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.setContext(this);
        db = new DataBaseHandler(this);
        next = true;
        l = (LinearLayout) findViewById(R.id.mainLayout);
        loadMovie();
    }

    public void deleteall(){
        db.clear();
        restart();
    }

    public void restart(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finishAffinity();
    }

    public void loadMovie(){
        names =db.getAllMovieList();
        i =0;
        if (names.size() == i) {

        } else {
            while(i<names.size()) {
                String s = names.get(i).getName();
                tempTitle =s;
                String u = names.get(i).getImageUrl();
                String d = names.get(i).getDecription();
                int id =names.get(i).getId();
               int no = names.get(i).getNo();
                makeMovie(s,d,u,id,no);

                i++;
            }
        }
    }

    public void makeMovie(String name,String description , String url,int id,int No) {

        ImageView image = new ImageView(this);
        TextView title = new TextView(this);
        TextView des = new TextView(this);
        LinearLayout ll = new LinearLayout(this);
        LinearLayout llinside = new LinearLayout(this);
        final Button button = new Button(this);

        resizeButton(button);
        resizeLinearLayoutinside(llinside);
        resizeTextDes(des);
        resizeLinearLayout(ll);
        resizeImageView(image);
        resizeTextView(title);

        addPicture(image, url);

        image.setTag(name);
        button.setTag(No);

        button.setText("details");
        button.setTextSize(13);
        title.setText(name);
        des.setText(description);

        final CustomDialog cdd = new CustomDialog(MainActivity.this, name, id);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEdit(view);
            }
        });
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                cdd.show();
                return false;
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int no = (int)button.getTag();
//                goToMoviePage(no);
//            }
//        });

        llinside.addView(title);
        llinside.addView(des);
        llinside.addView(button);

        ll.addView(image);
        ll.addView(llinside);

        l.addView(ll);
    }
    public void resizeButton(Button sv){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        sv.setLayoutParams(positionRules);
        positionRules.setMargins(10, 10, 10, 10);
    }

    public void resizeLinearLayoutinside(LinearLayout ll){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        ll.getLayoutParams().height = 880;
        positionRules.setMargins(25,0, 0, 5);
        ll.setOrientation(LinearLayout.VERTICAL);
    }

    public void resizeTextDes(TextView des){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        des.setLayoutParams(positionRules);
        des.setTextColor(Color.WHITE);
        des.setTextSize(13);
        positionRules.setMargins(5,5, 5, 5);
        des.getLayoutParams().height = 500;
    }

    public void resizeLinearLayout(LinearLayout ll){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(positionRules);
        positionRules.setMargins(25, 25, 25, 25);
        ll.setLayoutParams(positionRules);
        ll.getLayoutParams().height = 900;
       // ll.setBackgroundResource(R.raw.main);
    }

    public void resizeTextView(TextView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        b.setTextColor(Color.WHITE);
        b.setTextSize(25);
        positionRules.setMargins(15,0, 15, 15);
    }

    public void resizeImageView(ImageView b){
        LinearLayout.LayoutParams positionRules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        b.setLayoutParams(positionRules);
        positionRules.setMargins(15, 15, 25, 0);
        b.getLayoutParams().height = 800;
        b.getLayoutParams().width = 400;
    }

    public void addPicture(ImageView b,String u) {
        if (u.equals("")) {
          //  b.setBackgroundResource(R.drawable.nopic);
            b.getBackground().setAlpha(150);
        } else {
            new DownloadImageTask(this,l,this, b, u).execute();
        }
    }

//    public void goToMoviePage(int No){
//        Intent moviePage = new Intent(this,MoviePage.class);
//        moviePage.putExtra("No",No);
//        startActivity(moviePage);
//    }

    public void goToEdit(View v){
        String movieTitle = v.getTag().toString();

        for(i=0;i<names.size();i++){
            if(movieTitle.equals(names.get(i).getName())){
                String title =names.get(i).getName();
                String des =names.get(i).getDecription();
                String url =names.get(i).getImageUrl();
                int id = names.get(i).getId();

                Intent editActivity = new Intent(this,AddMovie.class);
                editActivity.putExtra("name",title);
                editActivity.putExtra("des",des);
                editActivity.putExtra("url",url);
                editActivity.putExtra("id",id);
                this.startActivityForResult(editActivity,1);

            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);


        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {

            case R.id.menuItemSettings:

                return true;
            case R.id.addMovieByInternet:
                Intent net = new Intent(this,InternetActivity.class);
                startActivity(net);
                return true;
            case R.id.addMovieByManual:
                Intent add = new Intent(this,AddMovie.class);
                add.putExtra("id",-1);
                startActivityForResult(add,1);
                return true;
            case R.id.exit:
                finish();
                return true;
            case R.id.deleteAll:
                deleteall();
                return true;

        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                int No=data.getIntExtra("No",0);
                String name=data.getStringExtra("name");
                String des=data.getStringExtra("des");
                String url1=data.getStringExtra("url");

                if(des.equals("")) {
                    if ( url1.equals("")) {
                        MovieSample movie = new MovieSample(name,"","",No);
                        db.addMovie(movie);
                    } else {
                        MovieSample movie = new MovieSample(name, "", url1,No);
                        db.addMovie(movie);
                    }
                }else if(url1.equals("")){
                    MovieSample movie = new MovieSample(name,des,"",No);
                    db.addMovie(movie);
                }else{
                    MovieSample movie = new MovieSample(name,des,url1,No);
                    db.addMovie(movie);
                }

                this.recreate();



            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }
}