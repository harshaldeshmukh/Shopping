package com.example.hp.shopping.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface FlowerApi {
@GET("/feeds/flowers.json")
    void  getFlower (Callback<String> flowers);

    @GET("/categories?visible=truecurrent_category_id=234&items_per_page=13")
    void  getCells (Callback<String> cells);
}
