package com.example.hp.shopping.Controller;

import android.util.Log;

import com.example.hp.shopping.Interfaces.CellI;
import com.example.hp.shopping.Interfaces.SliderImages;
import com.example.hp.shopping.Model.CellPhones;
import com.example.hp.shopping.Model.Flower;
import com.example.hp.shopping.Model.ImageSliders;
import com.example.hp.shopping.Model.Specific_Model;
import com.example.hp.shopping.Interfaces.Specificitem;
import com.example.hp.shopping.api.RestApiManager;

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
    private Specificitem specificitem;
    private SliderImages sliderImages;

    public Controller( FlowerCallbackListener mListener) {
        this.mListener = mListener;
        restApiManager= new RestApiManager();
    }

    public Controller( CellI cellI) {
        this.cellI=cellI;
        restApiManager= new RestApiManager();
    }

    public Controller( Specificitem specificitem) {
        this.specificitem=specificitem;
        restApiManager= new RestApiManager();
    }

    public Controller( SliderImages sliderImages) {
        this.sliderImages=sliderImages;
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

    public  void  startcategorycellfeching(String category_id){
        restApiManager.getPhoneApi().getCategoryCells(category_id,new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                Log.d(TAG, "JSON ::::" + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("products");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);



                        JSONObject productJsonObject = object.getJSONObject("main_pair");

                        JSONObject productJsonObject1 = productJsonObject.getJSONObject("detailed");
                        Specific_Model specificModel = new Specific_Model.Builder()  .setProduct(object.getString("product")).setProduct_id(object.getString("product_id"))
                                .setProduct_code(object.getString("product_code")).setFree_shipping(object.getString("free_shipping"))
                                .setPrice(object.getString("price"))
                                .setImage_path(productJsonObject1.getString("image_path"))

                                .build();
                        specificitem.onFetchProgress(specificModel);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                    specificitem.onFetchFailed();
                }
                specificitem.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                specificitem.onFetchComplete();

            }
        });
    }


//    public void sliderimagefetch(){
//        restApiManager.getPhoneApi().getSilderImages(new Callback<String>() {
//            @Override
//            public void success(String s, Response response) {
//                Log.d(TAG, "JSON ::::" + s);
//                try {
//                    JSONObject jsonRoot = new JSONObject(s);
//                    JSONObject jsonObject = jsonRoot.getJSONObject("main_pair");
//                    JSONObject productJsonObject1 = jsonObject.getJSONObject("detailed");
//                        ImageSliders imageSliders = new ImageSliders.Builder() .addPath(productJsonObject1.getString("image_path"))
//                                .build();
//                        sliderImages.onFetchProgress(imageSliders);
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                    sliderImages.onFetchFailed();
//                }
//                sliderImages.onFetchComplete();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d(TAG, "Error :: " + error.getMessage());
//                sliderImages.onFetchComplete();
//
//            }
//        });
//    }



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
