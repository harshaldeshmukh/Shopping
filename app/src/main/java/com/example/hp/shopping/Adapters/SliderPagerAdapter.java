package com.example.hp.shopping.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hp.shopping.Model.CellPhones;
import com.example.hp.shopping.Model.ImageSliders;
import com.example.hp.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    Activity activity;
   // ArrayList<String> image_arraylist;
   public List<String> imageSliderslist;


    public SliderPagerAdapter(Activity activity, ArrayList<String> image_arraylist) {
        this.activity = activity;
        this.imageSliderslist = image_arraylist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_slider,container,false);
        ImageView im_slider = (ImageView) view.findViewById(R.id.im_slider);
        Picasso.with(activity.getApplicationContext())
                .load(imageSliderslist.get(position))
                .into(im_slider);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imageSliderslist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
