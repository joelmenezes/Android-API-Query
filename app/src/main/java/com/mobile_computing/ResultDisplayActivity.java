package com.mobile_computing;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import android.database.sqlite.*;
import android.widget.Toast;

public class ResultDisplayActivity extends AppCompatActivity {

    private FavoriteDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        datasource = new FavoriteDataSource(this);
        datasource.open();

        //Back Button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Extract data from the intent
        Intent in = getIntent();
        final String title = in.getStringExtra("title");
        final String date = in.getStringExtra("date");
        final String text = in.getStringExtra("text");
        final String URL = in.getStringExtra("img");

        getSupportActionBar().setTitle("Book: " + title);

        //Load image using the link in the API
        ImageLoader imgLoad = VolleySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        NetworkImageView img = (NetworkImageView) findViewById(R.id.image);
        img.setDefaultImageResId(R.mipmap.ic_launcher);
        img.setImageUrl(URL, imgLoad);

        //Create and link TextViews
        TextView t = (TextView) findViewById(R.id.title);
        TextView d = (TextView) findViewById(R.id.date);
        TextView txt = (TextView) findViewById(R.id.text);

        //Set data to the TextViews
        t.setText(title);
        d.setText(date);
        txt.setText(text);



        final CheckBox starButton = (CheckBox)findViewById(R.id.starButton);
        starButton.setChecked(false);

        starButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //insert favorite to db
                if(b = true){

                    Favorite favorite = null;

                    datasource.createFavorite(title);


                    //String msg = "Favorited";
                //Toast toast = Toast.makeText(ResultDisplayActivity.this, msg, Toast.LENGTH_SHORT);
                //toast.show();
                //String title1 = title;
                }

            }
        });

        TextView backBtn = (TextView) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }





}
