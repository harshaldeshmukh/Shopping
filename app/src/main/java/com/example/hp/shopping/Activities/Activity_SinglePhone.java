package com.example.hp.shopping.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hp.shopping.Adapters.SpeicificItemAdapter;
import com.example.hp.shopping.Controller.Controller;
import com.example.hp.shopping.Interfaces.Specificitem;
import com.example.hp.shopping.Model.Specific_Model;
import com.example.hp.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_SinglePhone extends AppCompatActivity implements Specificitem {
    Toolbar toolbar;
    TextView mTitle;
    String toolbartitle,category_id;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Specific_Model> specificModelList = new ArrayList<>();

    public SpeicificItemAdapter speicificItemAdapter;
    public Controller mController;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__single_phone);

        toolbar = (Toolbar)findViewById(R.id.toolbarsss);

        toolbartitle=getIntent().getStringExtra("headline");
        category_id=getIntent().getStringExtra("id");
         mTitle = (TextView) findViewById(R.id.toolbar_title);
         setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle.setText(toolbartitle);
        mController = new Controller(Activity_SinglePhone.this);

        mController.startcategorycellfeching(category_id);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh) ;

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        speicificItemAdapter = new SpeicificItemAdapter(getApplicationContext(),specificModelList);
        recyclerView.setAdapter(speicificItemAdapter);

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                specificModelList.clear();
                mController.startcategorycellfeching(category_id);
            }
        });

    }

    @Override
    public void onFetchProgress(Specific_Model specific_model) {
        speicificItemAdapter.addItem(specific_model);
    }

    @Override
    public void onFetchProgress1(List<Specific_Model> specificModelList) {


    }

    @Override
    public void onFetchComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }
}
