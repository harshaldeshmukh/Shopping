package com.example.hp.shopping.api;

import retrofit.Callback;
import retrofit.http.GET;

public interface FlowerApi {
@GET("/feeds/flowers.json")
    void  getFlower (Callback<String> flowers);

    @GET("/categories?visible=true&current_category_id=234&items_per_page=13")
    void  getCells (Callback<String> cells);

    @GET("/categories/343/products&items_per_page=10?status=A")
    void  getCategoryCells (Callback<String> cells);
}
