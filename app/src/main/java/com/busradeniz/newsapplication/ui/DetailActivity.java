package com.busradeniz.newsapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.busradeniz.newsapplication.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imgArticle = findViewById(R.id.imgArticle);
        TextView txtTitle = findViewById(R.id.txtTitle);
        TextView txtDescription = findViewById(R.id.txtDescription);


        String title = getIntent().getStringExtra("TITLE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");

        txtTitle.setText(title);
        txtDescription.setText(description);

        Glide.with(this)
                .load(imageUrl)
                .into(imgArticle);
    }

}
