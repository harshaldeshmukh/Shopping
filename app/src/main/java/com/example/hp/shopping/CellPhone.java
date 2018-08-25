package com.example.hp.shopping;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.shopping.Controller.Controller;
import com.example.hp.shopping.Model.CellPhones;

import java.util.ArrayList;
import java.util.List;

public class CellPhone extends AppCompatActivity implements CellI {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<CellPhones> cellPhonesList = new ArrayList<>();

    public   CellAdapter CellAdapter;
    public Controller mController;

    LinearLayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone);
        mController = new Controller(CellPhone.this);
        configViews();
        mController.startcellfeching();
    }

    private void configViews() {

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh) ;

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());




        CellAdapter = new CellAdapter(cellPhonesList);
        recyclerView.setAdapter(CellAdapter);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startcellfeching();
            }
        });


    }

    @Override
    public void onFetchProgress1(CellPhones cellPhones) {
        CellAdapter.addCell(cellPhones);
    }

    @Override
    public void onFetchProgress1(List<CellPhones> cellPhonesList) {

    }

    @Override
    public void onFetchComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }
}
