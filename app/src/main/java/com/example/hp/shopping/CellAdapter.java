package com.example.hp.shopping;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hp.shopping.Model.CellPhones;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.Holder> {

    public static String TAG = FlowerAdapter.class.getSimpleName();
    private List<CellPhones> cellPhoneslist;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    public CellAdapter(List<CellPhones> cellPhones) {
        this.cellPhoneslist = cellPhones;
//
    }

    @Override
    public int getItemViewType(int position) {
        return cellPhoneslist.get(position) != null ? VIEW_ITEM : VIEW_PROG;
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
        CellPhones currentFlower = cellPhoneslist.get(position);

        holder.flowerName.setText(currentFlower.category_id);
        holder.flowerCategory.setText(currentFlower.category);
        holder.flowerPrice.setVisibility(View.GONE);
        holder.flowerInstruction.setVisibility(View.GONE);
       // Picasso.with(holder.itemView.getContext()).load(Constants.PHOTO_URL + currentFlower.mPhoto).into(holder.flowerImage);

    }
    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return cellPhoneslist.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void addCell(CellPhones cellPhones) {

        cellPhoneslist.add(cellPhones);
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
