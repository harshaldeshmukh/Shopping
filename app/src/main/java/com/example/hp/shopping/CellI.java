package com.example.hp.shopping;

import com.example.hp.shopping.Model.CellPhones;

import java.util.List;

public interface CellI {
    void onFetchProgress1(CellPhones cellPhones);
    void onFetchProgress1(List<CellPhones> cellPhonesList);
    void onFetchComplete();
    void onFetchFailed();
}
