package com.example.hp.shopping.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageSliders implements Serializable {
    private  String company_name;

    private ArrayList<String> Path = new ArrayList<String>();

    public ArrayList<String> getPath() {
        return Path;
    }

    public void addPath(String path)
    {
        Path.add(path);
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
