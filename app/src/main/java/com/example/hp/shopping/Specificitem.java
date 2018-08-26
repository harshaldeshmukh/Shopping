package com.example.hp.shopping;

import com.example.hp.shopping.Model.Specific_Model;

import java.util.List;

public interface Specificitem {
    void onFetchProgress(Specific_Model specific_model);
    void onFetchProgress1(List<Specific_Model> specificModelList);
    void onFetchComplete();
    void onFetchFailed();
}
