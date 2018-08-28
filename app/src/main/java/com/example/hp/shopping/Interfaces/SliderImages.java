package com.example.hp.shopping.Interfaces;

import com.example.hp.shopping.Model.ImageSliders;

import java.util.List;

public interface SliderImages {
    void onFetchProgress(ImageSliders imageSliders);
    void onFetchProgress1(List<ImageSliders> specificModelList);
    void onFetchComplete();
    void onFetchFailed();
}
