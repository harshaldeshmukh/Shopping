package com.example.hp.shopping.Activities;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.shopping.Adapters.CellAdapter;
import com.example.hp.shopping.Controller.Controller;
import com.example.hp.shopping.Model.Flower;
import com.example.hp.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class Activity_Register extends AppCompatActivity implements Controller.FlowerCallbackListener {
  private   RecyclerView recyclerView;
  private SwipeRefreshLayout swipeRefreshLayout;
  private List<Flower> mflowerList = new ArrayList<>();

  public CellAdapter.FlowerAdapter flowerAdapter;
    public Controller mController;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;
    protected Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);

        mController = new Controller(Activity_Register.this);
        configViews();
        mController.StartFetching();
    }

    private void configViews() {

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swiperefresh) ;

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( visibleItemCount == 10)
                        {
                            loading = false;


                            // Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                        }
                    }
                }
            }
        });



        flowerAdapter = new CellAdapter.FlowerAdapter(mflowerList,recyclerView);
        recyclerView.setAdapter(flowerAdapter);
//        flowerAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                //add null , so the adapter will check view_type and show progress bar at bottom
//                mflowerList.add(null);
//                flowerAdapter.notifyItemInserted(mflowerList.size() - 1);
//
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //   remove progress item
//                        mflowerList.remove(mflowerList.size() - 1);
//                        flowerAdapter.notifyItemRemoved(mflowerList.size());
//                        //add items one by one
//                        int start = mflowerList.size();
//                        int end = start + 5;
//
//                        for (int i = start + 1; i <= end; i++) {
//                           // mflowerList.add(new Flower());
//                            flowerAdapter.notifyItemInserted(mflowerList.size());
//                        }
//                        flowerAdapter.setLoaded();
//                        //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
//                    }
//                }, 2000);
//
//            }
//        });




        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.StartFetching();
            }
        });


    }

    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Flower flower) {
        flowerAdapter.addFlower(flower);

    }

    @Override
    public void onFetchProgress(List<Flower> flowerList) {

    }

    @Override
    public void onFetchComplete() {
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onFetchFailed() {

    }
}
