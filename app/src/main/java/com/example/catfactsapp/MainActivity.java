package com.example.catfactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements CatContent.CatContentListener, BreedSelector.BreedSelectorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public ImageView getCatPix(){
        return CatContent.getCatPix();
    }

    public TextView getCatBreedTitle(){
        return CatContent.getCatBreedTitle();
    }

    public TextView getTemperamentBody(){
        return CatContent.getTemperamentBody();
    }

    public TextView getOriginBody(){
        return CatContent.getOriginBody();
    }

    public void getUpdateCatContent(ImageView catPix, TextView catBreedTitle, TextView temperamentBody, TextView originBody, Vector<Cat> myCats, int position){
        CatContent.updateCatContent(catPix, catBreedTitle, temperamentBody, originBody, myCats, position);
    }
}