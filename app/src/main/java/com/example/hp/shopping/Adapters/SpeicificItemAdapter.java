package com.example.hp.shopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.shopping.Activities.Activity_Details;
import com.example.hp.shopping.Utils.AppData;
import com.example.hp.shopping.Model.Specific_Model;
import com.example.hp.shopping.Interfaces.OnLoadMoreListener;
import com.example.hp.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpeicificItemAdapter extends RecyclerView.Adapter<SpeicificItemAdapter.Holder> {

    public static String TAG = CellAdapter.FlowerAdapter.class.getSimpleName();
    private List<Specific_Model> specificModelList;
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private OnLoadMoreListener onLoadMoreListener;
    Context context;
    AppData appData;

    public SpeicificItemAdapter(Context context, List<Specific_Model> specificModels) {
        this.context=context;
        this.specificModelList = specificModels;
        initFonts();
//
    }
    protected void initFonts() {
        appData = AppData.getInstance(context);
    }

    @Override
    public int getItemViewType(int position) {
        return specificModelList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public SpeicificItemAdapter.Holder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        SpeicificItemAdapter.Holder holder;
        if (viewType == VIEW_ITEM) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
            holder = new SpeicificItemAdapter.Holder(view);
        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.progressbar, viewGroup, false);
            holder = new SpeicificItemAdapter.Holder(v);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(SpeicificItemAdapter.Holder holder, int position) {
        final   Specific_Model specificModel = specificModelList.get(position);

        holder.productName.setText(specificModel.product);
        holder.productName.setTypeface(appData.getFontSemiBolad());
        holder.product_code.setText(specificModel.product_code);
        holder.product_code.setTypeface(appData.getFontRegular());
        holder.free_shipping.setText(specificModel.free_shipping);
        holder.free_shipping.setTypeface(appData.getFontRegular());
        holder.price.setText("\u20B9 "+specificModel.price);
        holder.price.setTypeface(appData.getFontBold());
        Picasso.with(holder.itemView.getContext()).load(specificModel.image_path).into(holder.imageView);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Activity_Details.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                i.putExtra("id",specificModel.product_id);
//                i.putExtra("headline",currentFlower.category);
//                i.putExtra("id",currentFlower.category_id);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return specificModelList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void addItem(Specific_Model specific_model) {

        specificModelList.add(specific_model);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView productName,product_code,free_shipping,price;
        CardView cardview;
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.flowerName);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
            product_code = (TextView) itemView.findViewById(R.id.flowerCategory);
            free_shipping = (TextView) itemView.findViewById(R.id.flowerPrice);
            price = (TextView) itemView.findViewById(R.id.flowerInstruction);
            imageView = (ImageView) itemView.findViewById(R.id.flowerImage);
        }


    }


}
