package com.example.hp.shopping.Utils;

import android.content.Context;
import android.graphics.Typeface;

public class AppData {
    private static AppData instance;
    private Context context;
    private Typeface fontRegular;
    private Typeface fontSemiBolad;
    private Typeface fontBold;


    private AppData(Context  context )
    {
        this.context = context;
        initFonts();
    }

    public static AppData getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new AppData(context);
        }
        return instance;
    }

    private void initFonts()
    {
        setFontRegular(Typeface.createFromAsset(context.getAssets(), "OpenSansRegular.ttf"));

        setFontSemiBolad(Typeface.createFromAsset(context.getAssets(), "OpenSansSemibold.ttf"));

        setFontBold(Typeface.createFromAsset(context.getAssets(), "OpenSansBold.ttf"));

    }

    public Typeface getFontRegular() {
        return fontRegular;
    }

    public void setFontRegular(Typeface fontRegular) {
        this.fontRegular = fontRegular;
    }

    public Typeface getFontSemiBolad() {
        return fontSemiBolad;
    }

    public void setFontSemiBolad(Typeface fontSemiBolad) {
        this.fontSemiBolad = fontSemiBolad;
    }

    public Typeface getFontBold() {
        return fontBold;
    }

    public void setFontBold(Typeface fontBold) {
        this.fontBold = fontBold;
    }



}
