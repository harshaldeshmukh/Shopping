package com.example.hp.shopping;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hp.shopping.Model.Flower;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FlowerAdapter  extends RecyclerView.Adapter<FlowerAdapter.Holder> {

    public static String TAG = FlowerAdapter.class.getSimpleName();
    private List<Flower> mflowwr;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public FlowerAdapter(List<Flower> mflowwr, RecyclerView recyclerView) {
        this.mflowwr = mflowwr;
//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//
//            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
//                    .getLayoutManager();
//
//
//            recyclerView
//                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
//                        @Override
//                        public void onScrolled(RecyclerView recyclerView,
//                                               int dx, int dy) {
//                            super.onScrolled(recyclerView, dx, dy);
//
//                            totalItemCount = linearLayoutManager.getItemCount();
//                            lastVisibleItem = linearLayoutManager
//                                    .findLastVisibleItemPosition();
//                            if (!loading
//                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                                // End has been reached
//                                // Do something
//                                if (onLoadMoreListener != null) {
//                                    onLoadMoreListener.onLoadMore();
//                                }
//                                loading = true;
//                            }
//                        }
//                    });
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return mflowwr.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Holder holder;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
            holder = new Holder(view);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.progressbar, viewGroup, false);
            holder = new Holder(v);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Flower currentFlower = mflowwr.get(position);

        holder.flowerName.setText(currentFlower.mName);
        holder.flowerCategory.setText(currentFlower.mcategory);
        holder.flowerPrice.setText(Double.toString(currentFlower.mPrice));
        holder.flowerInstruction.setText(currentFlower.mInstructions);
        Picasso.with(holder.itemView.getContext()).load(Constants.PHOTO_URL + currentFlower.mPhoto).into(holder.flowerImage);

    }
    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return mflowwr.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void addFlower(Flower flower) {

        mflowwr.add(flower);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView flowerName, flowerCategory, flowerPrice, flowerInstruction;
        ImageView flowerImage;

        public Holder(View itemView) {
            super(itemView);

            flowerName = (TextView) itemView.findViewById(R.id.flowerName);
            flowerCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
            flowerPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            flowerInstruction = (TextView) itemView.findViewById(R.id.flowerInstruction);
            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
        }


    }

    public  class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}


