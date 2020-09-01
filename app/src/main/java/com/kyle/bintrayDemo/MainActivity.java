package com.kyle.bintrayDemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.kyle.core.GlideImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((GlideImageView)findViewById(R.id.image)).setImageUri("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=191936283,2923048863&fm=26&gp=0");
    }
}
