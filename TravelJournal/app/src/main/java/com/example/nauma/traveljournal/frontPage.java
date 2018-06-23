package com.example.nauma.traveljournal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class frontPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<String> posts = new ArrayList<>();
    ArrayAdapter<String> adapter;

    public static int firstTime = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView entryList = (ListView) findViewById(R.id.entryList);

        if (firstTime == 1) {
            loadSavedPreferences();
            firstTime = 0; }
            entryList.setAdapter(adapter);

        if(posts != null && posts.size() >0) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, posts.toArray(new String[1]));
            entryList.setAdapter(adapter);
        }

        entryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posts.remove(position);
                adapter.notifyDataSetChanged();

                Toast.makeText(frontPage.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newItemPage = new Intent(frontPage.this, createPost.class);
                startActivity(newItemPage);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent newItemPage = new Intent(frontPage.this, takePicture.class);
            startActivity(newItemPage);
        }// else if (id == R.id.nav_gallery) {
           // Intent newItemPage = new Intent(frontPage.this, galleryPage.class);
            //startActivity(newItemPage);
        //}
        else if (id == R.id.nav_slideshow) {
            Intent maps = new Intent(frontPage.this, MapsActivity.class);
            startActivity(maps);
        } else if (id == R.id.nav_manage) {
            Intent instructions = new Intent(frontPage.this, instructionsPage.class);
            startActivity(instructions);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void savePreferences() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String entryString = "";

        for(int i = 0; i < posts.size(); i++) {
            entryString += posts.get(i) + "|";
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("posts", entryString);
        editor.apply();
    }

    private void loadSavedPreferences() {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String entryString = sharedPreferences.getString("posts", "");
        StringTokenizer tokenizer = new StringTokenizer(entryString, "|");
        while(tokenizer.hasMoreTokens()) {
            posts.add(tokenizer.nextToken());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        savePreferences();
        finish();
    }
}
