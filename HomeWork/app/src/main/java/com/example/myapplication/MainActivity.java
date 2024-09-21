package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.iamgeview);

        Button buttonGlide = findViewById(R.id.button2);
        Button buttonPicasso = findViewById(R.id.button4);
        Button buttonGlideError = findViewById(R.id.button1);
        Button buttonPicassoError = findViewById(R.id.button3);

        String validUrl = "https://sun1-98.userapi.com/s/v1/ig2/P2lRHh7-_xXHASw932wso4QiL2O6vBu4959XT7EQh-13Chk8S4blDFgwriM1qGBJ1zW9N2lLKa26Cl1Irsy6Cvpp.jpg?quality=95&as=32x25,48x38,72x56,108x85,160x125,240x188,360x282,480x376,540x423,640x501,720x563,800x626&from=bu&u=Eqv7RBteYk4w0B5dzdJDKlWFt4y8SUxnC8vpSqWUa5s&cs=800x626";
        String invalidUrl = "https://example.com/invalid_image.jpg";

        buttonGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this)
                        .load(validUrl)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.loading_placeholder)
                                .error(R.drawable.error_placeholder)
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Toast.makeText(MainActivity.this, "Glide failed to load image", Toast.LENGTH_SHORT).show();
                                Log.e("GlideError", "Error loading image", e);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                Toast.makeText(MainActivity.this, "Glide image loaded successfully", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        })
                        .into(imageView);
            }
        });


        buttonPicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get()
                        .load(validUrl)
                        .placeholder(R.drawable.loading_placeholder)
                        .error(R.drawable.error_placeholder)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "Picasso image loaded successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(MainActivity.this, "Picasso failed to load image", Toast.LENGTH_SHORT).show();
                                Log.e("PicassoError", "Error loading image", e);
                            }
                        });
            }
        });


        buttonGlideError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(MainActivity.this)
                        .load(invalidUrl)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.loading_placeholder)
                                .error(R.drawable.error_placeholder)
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(imageView);
            }
        });

        buttonPicassoError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get()
                        .load(invalidUrl)
                        .placeholder(R.drawable.loading_placeholder)
                        .error(R.drawable.error_placeholder)
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "Picasso image loaded successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(MainActivity.this, "Picasso failed to load image", Toast.LENGTH_SHORT).show();
                                Log.e("PicassoError", "Error loading image", e);
                            }
                        });
            }
        });
    }
}
