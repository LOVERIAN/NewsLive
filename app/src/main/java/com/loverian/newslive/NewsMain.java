package com.loverian.newslive;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class NewsMain extends AppCompatActivity {

    // Main Activity
    // Sets Bottom Navigation view and fragments

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    Headlines headlines;
    Livetv livetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        bottomNavigationView = findViewById(R.id.nav_bar);
        frameLayout = findViewById(R.id.container);

        headlines =new Headlines();
        livetv = new Livetv();

        //setting initial fragment
        setFragment(headlines);

        // On click listener for navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.headline :
                        setFragment(headlines);
                        return true;

                    case R.id.live:
                        setFragment(livetv);
                        return true;

                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(Fragment fragment) {
        //sets fragments according to view click
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu ){
        // Creates option menu for privacy policy
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opt,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.privacy) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://newslivetvprivacy.blogspot.com/2019/09/privacy-policy-loverian-built-news-live.html"));
            startActivity(intent);
            return true;
        } else return false;
    }

}