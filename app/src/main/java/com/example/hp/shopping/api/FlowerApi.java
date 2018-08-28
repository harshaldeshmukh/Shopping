package com.example.hp.shopping.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface FlowerApi {
@GET("/feeds/flowers.json")
    void  getFlower (Callback<String> flowers);

    @GET("/categories?visible=true&current_category_id=234&items_per_page=13")
    void  getCells (Callback<String> cells);

    @GET("/categories/{category_id}/products&items_per_page=10?status=A")
    void  getCategoryCells (@Path("category_id") String name , Callback<String> cells);

    @GET("/products/922")
    void  getSilderImages (Callback<String> slider);
}
