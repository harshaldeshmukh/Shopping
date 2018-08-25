package com.example.hp.shopping.Controller;

import android.util.Log;

import com.example.hp.shopping.CellI;
import com.example.hp.shopping.CellPhone;
import com.example.hp.shopping.Model.CellPhones;
import com.example.hp.shopping.Model.Flower;
import com.example.hp.shopping.api.RestApiManager;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Controller {
    private   static  final  String TAG=Controller.class.getSimpleName();
   private RestApiManager restApiManager;
    private FlowerCallbackListener mListener;
    private CellI cellI;

    public Controller( FlowerCallbackListener mListener) {

        this.mListener = mListener;

        this.cellI=cellI;

        restApiManager= new RestApiManager();
    }

    public Controller( CellI cellI) {

        this.cellI=cellI;

        restApiManager= new RestApiManager();
    }


    public  void  StartFetching(){
        restApiManager.getFlowerApi().getFlower(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON ::::" + s);
                try {

                    JSONArray jsonArray = new JSONArray(s);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        Flower flower = new Flower.Builder() .setCategory(object.getString("category"))
                                .setPrice(object.getDouble("price"))
                                .setInstructions(object.getString("instructions"))
                                .setPhoto(object.getString("photo"))
                                .setName(object.getString("name"))
                                .setProductId(object.getInt("productId"))
                                .build();

                        mListener.onFetchProgress(flower);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    mListener.onFetchFailed();
                }

                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();

            }
        });
    }


    public  void  startcellfeching(){
        restApiManager.getPhoneApi().getCells(new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON ::::" + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);

                    JSONArray jsonArray = jsonObject.getJSONArray("categories");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject object = jsonArray.getJSONObject(i);

                        CellPhones cellPhones = new CellPhones.Builder()  .setCategory(object.getString("category"))
                                .setCategory_id(object.getString("category_id"))

                                .build();


                        cellI.onFetchProgress1(cellPhones);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    cellI.onFetchFailed();
                }

                cellI.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                cellI.onFetchComplete();

            }
        });
    }



    public interface FlowerCallbackListener {

        void onFetchStart();
        void onFetchProgress(Flower flower);
       // void onFetchProgress1(CellPhones cellPhones);
        void onFetchProgress(List<Flower> flowerList);
       // void onFetchProgress1(List<CellPhones> cellPhonesList);
        void onFetchComplete();
        void onFetchFailed();

    }

}
