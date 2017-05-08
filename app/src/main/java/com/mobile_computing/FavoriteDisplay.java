package com.mobile_computing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class FavoriteDisplay extends ListActivity {
    private FavoriteDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        datasource = new FavoriteDataSource(this);
        datasource.open();

        List<Favorite> values = datasource.getAllFavorites();

        // use the SimpleCursorAdapter to show favorites in a ListView
        ArrayAdapter<Favorite> adapter = new ArrayAdapter<Favorite>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}