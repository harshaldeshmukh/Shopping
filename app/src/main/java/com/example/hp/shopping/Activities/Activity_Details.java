package com.example.hp.shopping.Activities;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hp.shopping.Adapters.SliderPagerAdapter;
import com.example.hp.shopping.Controller.Controller;
import com.example.hp.shopping.Interfaces.SliderImages;
import com.example.hp.shopping.Model.ImageSliders;
import com.example.hp.shopping.R;
import com.example.hp.shopping.Utils.AppData;
import com.example.hp.shopping.VolleyApplication;
import com.viewpagerindicator.LinePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Activity_Details extends AppCompatActivity implements SliderImages {

    private ViewPager vp_slider;

    SliderPagerAdapter sliderPagerAdapter;
    ImageSliders imageSliders;
    Controller controller;
    ArrayList<String> images;
    TabLayout tabDots;
    String product_id;
    TextView producttitle;
    AppData appData;
    LinePageIndicator linePageIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__details);
        initFonts();
        initUi();
        controller = new Controller(Activity_Details.this);

        fetchAllProducts();

    }

  public  void initUi(){
      images=new ArrayList<>();
      product_id=getIntent().getStringExtra("id");
      producttitle=(TextView)findViewById(R.id.producttitle);
      vp_slider = (ViewPager) findViewById(R.id.vp_slider);
     // ll_dots = (LinearLayout) findViewById(R.id.ll_dots);
     // tabDots=(TabLayout)findViewById(R.id.tabDots);
      linePageIndicator=(LinePageIndicator)findViewById(R.id.pageIndicator);

    }

    protected void initFonts() {
        appData = AppData.getInstance(getApplicationContext());
    }

    private void fetchAllProducts() {
        JsonObjectRequest request = new JsonObjectRequest(
                "http://mymartmycart.com/api/products/"+product_id,
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            ImageSliders imageRecords = parseAll(jsonObject);
                            producttitle.setText(imageRecords.getProduct_name());
                            producttitle.setTypeface(appData.getFontSemiBolad());
                            images= imageRecords.getPath();
                                    sliderPagerAdapter= new SliderPagerAdapter(Activity_Details.this,images);
                            vp_slider.setAdapter(sliderPagerAdapter);
                            linePageIndicator.setViewPager(vp_slider);

                        }
                        catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Unable to parse data: " + e.getMessage(), Toast.LENGTH_SHORT).show();

                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();

                return headers;
            }};
        VolleyApplication.getInstance().getRequestQueue().add(request);
    }


    private ImageSliders parseAll(JSONObject json) throws JSONException {
        ImageSliders imageSliders = new ImageSliders();
        imageSliders.setCompany_name(json.getString("company_name"));
        imageSliders.setProduct_name(json.getString("product"));
        JSONObject jsonObject = json.getJSONObject("main_pair");
        JSONObject productJsonObject1 = jsonObject.getJSONObject("detailed");
        imageSliders.addPath(productJsonObject1.getString("image_path"));
        JSONObject jsonObject2 = json.optJSONObject("image_pairs");
        if (jsonObject2 == null) {
            return imageSliders;
        }
        Iterator<String> keys = jsonObject2.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject inside = jsonObject2.getJSONObject(key);
            JSONObject jsonObject3 = inside.getJSONObject("detailed");
            imageSliders.addPath(jsonObject3.getString("image_path"));
        }
        return imageSliders;
    }


    @Override
    public void onFetchProgress(ImageSliders imageSliders) {

    }

    @Override
    public void onFetchProgress1(List<ImageSliders> specificModelList) {

    }

    @Override
    public void onFetchComplete() {

    }

    @Override
    public void onFetchFailed() {

    }
}
