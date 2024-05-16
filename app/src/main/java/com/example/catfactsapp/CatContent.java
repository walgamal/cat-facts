package com.example.catfactsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Vector;

public class CatContent extends Fragment {

    private static CatContentListener listener;
    private static ImageView catPix;
    private static TextView catBreedTitle;
    private static TextView temperamentBody;
    private static TextView originBody;

    public CatContent() {
        // Required empty public constructor
    }

    public static ImageView getCatPix() {
        return catPix;
    }

    public static TextView getCatBreedTitle() {
        return catBreedTitle;
    }

    public static TextView getTemperamentBody() {
        return temperamentBody;
    }

    public static TextView getOriginBody() {
        return originBody;
    }

    public interface CatContentListener{   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cat_content, container, false);
        CatApiService catApiService = new CatApiService(view.getContext());

        //Creating editable views
        catPix = view.findViewById(R.id.CatPix);
        catBreedTitle = view.findViewById(R.id.CatBreedTitle);
        temperamentBody = view.findViewById(R.id.TemperamentBody);
        originBody = view.findViewById(R.id.OriginBody);

        // Inflate the layout for this fragment
        return view;
    }

    public static void updateCatContent(ImageView catPix, TextView catBreedTitle, TextView temperamentBody, TextView originBody, Vector<Cat> myCats, int position){
        int selected = position;

        Picasso.get().load(myCats.get(selected).imageUrl).into(catPix);

        catBreedTitle.setText(myCats.get(selected).name);
        temperamentBody.setText(myCats.get(selected).temperament);
        originBody.setText(myCats.get(selected).origin);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CatContent.CatContentListener){
            listener = (CatContent.CatContentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CatContentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}