package com.example.hp.shopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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
    Context context;

    public CellAdapter(Context context, List<CellPhones> cellPhones) {
        this.context=context;
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
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cell, viewGroup, false);
            holder = new Holder(view);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.progressbar, viewGroup, false);
            holder = new Holder(v);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
     final   CellPhones currentFlower = cellPhoneslist.get(position);

        holder.flowerName.setText(currentFlower.category+" "+currentFlower.category_id);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Activity_SinglePhone.class);
                i.putExtra("headline",currentFlower.category);
                i.putExtra("id",currentFlower.category_id);
                context.startActivity(i);

            }
        });

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
        TextView flowerName;
        CardView cardview;

        public Holder(View itemView) {
            super(itemView);

            flowerName = (TextView) itemView.findViewById(R.id.flowerName);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
//            flowerCategory = (TextView) itemView.findViewById(R.id.flowerCategory);
//            flowerPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
//            flowerInstruction = (TextView) itemView.findViewById(R.id.flowerInstruction);
//            flowerImage = (ImageView) itemView.findViewById(R.id.flowerImage);
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
