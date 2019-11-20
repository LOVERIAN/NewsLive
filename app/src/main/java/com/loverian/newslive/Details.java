package com.loverian.newslive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_details);
        ImageView imageView = findViewById(R.id.main_backdrop);
        TextView textView = findViewById(R.id.decs);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
//this line shows back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getExtras();
        Picasso.get()
                .load(bundle.getString("img"))
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .fit()
                .into(imageView);
        toolbar.setTitle(bundle.getString("source"));
        String dec = bundle.getString("dec") + " \n" + bundle.getString("content");
        textView.setText(dec);
        TextView url = findViewById(R.id.url);
        url.setText("\nCheck full article here -> " + bundle.getString("url"));

    }

}
