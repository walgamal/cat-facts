package com.example.catfactsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class BreedSelector extends Fragment {

    private BreedSelectorListener listener;
    private Spinner breedSpinner;
    protected ArrayAdapter<String> spinnerAdapter;
    public static ArrayList<String> catBreeds = new ArrayList<>();

    public BreedSelector() {
        // Required empty public constructor
    }

    public interface BreedSelectorListener{
        ImageView getCatPix();
        TextView getCatBreedTitle();
        TextView getTemperamentBody();
        TextView getOriginBody();
        void getUpdateCatContent(ImageView catPix, TextView catBreedTitle, TextView temperamentBody, TextView originBody, Vector<Cat> myCats, int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_breed_selector, container, false);
        breedSpinner = view.findViewById(R.id.BreedSpinner);

        CatApiService catApiService = new CatApiService(view.getContext());

        catApiService.getMyCats(new CatApiService.VolleyResponseListener() {
            @Override
            public void onError(String message) {
                System.out.println("SOMETHING WENT WRONG");
            }

            @Override
            public void onResponse(Vector<Cat> myCats) {
                for (int i = 0; i < myCats.size(); i++) {
                    catBreeds.add(myCats.get(i).name);
                }

                spinnerAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, catBreeds);
                setSpinnerAdapter(spinnerAdapter);
            }
        });

        breedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catApiService.getMyCats(new CatApiService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        System.out.println("SOMETHING WENT WRONG");
                    }

                    @Override
                    public void onResponse(Vector<Cat> myCats) {
                        listener.getUpdateCatContent(listener.getCatPix(), listener.getCatBreedTitle(), listener.getTemperamentBody(), listener.getOriginBody(), myCats, position);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void setSpinnerAdapter(ArrayAdapter<String> spinnerAdapter) {
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breedSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof BreedSelectorListener){
            listener = (BreedSelectorListener) context;
        } else {
            throw new RuntimeException(context + " must implement BreedSelectorListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}