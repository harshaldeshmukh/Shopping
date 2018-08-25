package com.example.hp.shopping.api;

import com.example.hp.shopping.Constants;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestApiManager {

    //Interface
    private  FlowerApi mFlowerApi;

    public FlowerApi getFlowerApi() {

        if(mFlowerApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mFlowerApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(FlowerApi.class);
        }
        return mFlowerApi;
    }

    public FlowerApi getPhoneApi() {

        if(mFlowerApi == null) {
            GsonBuilder gson = new GsonBuilder();
            gson.registerTypeAdapter(String.class, new StringDesirializer());

            mFlowerApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL1)
                    .setConverter(new GsonConverter(gson.create()))
                    .build()
                    .create(FlowerApi.class);
        }
        return mFlowerApi;
    }
}
