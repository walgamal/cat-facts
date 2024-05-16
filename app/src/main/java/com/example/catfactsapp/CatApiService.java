package com.example.catfactsapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class CatApiService {

    Context context;
    Vector<Cat> myCats = new Vector<>();

    public CatApiService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(Vector<Cat> myCats);
    }

    public void getMyCats(VolleyResponseListener volleyResponseListener){

        //------------------------------------Volley API Call------------------------------------\\
        String url = "https://api.thecatapi.com/v1/breeds?apiKey=d0a6b46e-e8b5-4926-bc11-c1f9cfae71c5";

        // Request a JSONArray response from the provided URL.
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    if(myCats.size() == 0) {
                                        myCats.ensureCapacity(response.length());
                                        for (int i = 0; i < response.length(); i++) {
                                            JSONObject responseObj = response.getJSONObject(i);

                                            if (responseObj.has("image")) {
                                                JSONObject tempImage = responseObj.getJSONObject("image");

                                                if (tempImage.has("url")) {
                                                    Cat newCat = new Cat(responseObj.getString("name"),
                                                            responseObj.getString("origin"),
                                                            responseObj.getString("temperament"),
                                                            tempImage.getString("url"));

                                                    myCats.add(newCat);
                                                }
                                            }
                                        }
                                    }
                                    volleyResponseListener.onResponse(myCats);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError("THAT DIDN'T WORK!");
                    }
                });

        // Add the request to the RequestQueue
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
