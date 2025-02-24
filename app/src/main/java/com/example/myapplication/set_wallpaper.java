package com.example.myapplication;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class set_wallpaper extends AppCompatActivity {
    Intent intent;
    ImageView imageView;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        set = findViewById(R.id.setwallpaper_btn);
        imageView = findViewById(R.id.setwallpaper_image); // Correct ID reference

        intent = getIntent();
        String imageUrl = intent.getStringExtra("image");

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this).load(imageUrl).into(imageView);
        } else {
            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
        }

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(set_wallpaper.this, "Wallpaper Set", Toast.LENGTH_SHORT).show();

                    // Navigate back to MainActivity after setting wallpaper
                    Intent intent = new Intent(set_wallpaper.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish(); // Close current activity
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(set_wallpaper.this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
