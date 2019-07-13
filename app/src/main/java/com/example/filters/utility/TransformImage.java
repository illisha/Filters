package com.example.filters.utility;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.filters.ControlActivity;
import com.squareup.picasso.Picasso;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.imageprocessors.subfilters.BrightnessSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.ContrastSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.SaturationSubFilter;
import com.zomato.photofilters.imageprocessors.subfilters.VignetteSubFilter;

public class TransformImage {
    public static final int MAX_BRIGHTNESS = 100;
    public static final int MAX_VIGNETTE = 255;
    public static final int MAX_SATURATION = 100;
    public static final int MAX_CONTRAST = 100;

    public static final int DEFAULT_BRIGHTNESS = 70;
    public static final int DEFAULT_VIGNETTE = 60;
    public static final int DEFAULT_SATURATION = 25;
    public static final int DEFAULT_CONTRAST = 66;

    private String mFilename;
    private Bitmap mBitmap;
    private Context mContext;

    private Bitmap brightnessFilteredBitmap;
    private Bitmap vignetteFilteredBitmap;
    private Bitmap saturationFilteredBitmap;
    private Bitmap contrastFilteredBitmap;

    public static int FILTER_BRIGHTNESS = 0;
    public static int FILTER_VIGNETTE = 2;
    public static int FILTER_SATURATION = 1;
    public static int FILTER_CONTRAST = 3;

    public String fileName(int filter)
    {
        if(filter == FILTER_BRIGHTNESS)
        {
            return mFilename+"_brightness.png";
        }
        else if (filter == FILTER_VIGNETTE)
        {
            return mFilename+"_vignette.png";
        }
        else if (filter == FILTER_SATURATION)
        {
            return mFilename+"_saturation.png";
        }
        else
        {
            return mFilename+"_contrast.png";
        }
    }

    public Bitmap getBitmap(int filter)
    {
        if(filter == FILTER_BRIGHTNESS)
        {
            return brightnessFilteredBitmap;
        }
        else if (filter == FILTER_VIGNETTE)
        {
            return vignetteFilteredBitmap;
        }
        else if (filter == FILTER_SATURATION)
        {
            return saturationFilteredBitmap;
        }
        else
        {
            return contrastFilteredBitmap;
        }
    }

    public TransformImage(Context context, Bitmap bitmap){
        mContext = context;
        mBitmap = bitmap;
        mFilename = System.currentTimeMillis() + "";
    }

    public Bitmap applyBrightnessSubFilter(int brightness) {
        Filter myFilter = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        //myFilter.addSubFilter(new BrightnessSubFilter(DEFAULT_BRIGHTNESS));
        myFilter.addSubFilter(new BrightnessSubFilter(brightness));
        Bitmap ouputImage = myFilter.processFilter(mutableBitmap);

        brightnessFilteredBitmap = ouputImage;

        return ouputImage;

    }

    public Bitmap applyVignetteSubFilter(int vignette) {
        Filter myFilterVignette = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        //myFilterVignette.addSubFilter(new VignetteSubFilter(mContext, DEFAULT_VIGNETTE));
        myFilterVignette.addSubFilter(new VignetteSubFilter(mContext, vignette));
        Bitmap outputImageVignette = myFilterVignette.processFilter(mutableBitmap);

        vignetteFilteredBitmap = outputImageVignette;

        return outputImageVignette;

    }

    public Bitmap applySaturationSubFilter(int saturation) {
        Filter myFilterSaturation = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        //myFilterSaturation.addSubFilter(new SaturationSubFilter(DEFAULT_SATURATION));
        myFilterSaturation.addSubFilter(new SaturationSubFilter(saturation));
        Bitmap outputImageSaturation = myFilterSaturation.processFilter(mutableBitmap);

        saturationFilteredBitmap = outputImageSaturation;

        return outputImageSaturation;

    }

    public Bitmap applyContrastSubFilter(int contrast) {
        Filter myFilterContrast = new Filter();

        Bitmap workingBitmap = Bitmap.createBitmap(mBitmap);
        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888,true);

        //myFilterContrast.addSubFilter(new ContrastSubFilter(DEFAULT_CONTRAST));
        myFilterContrast.addSubFilter(new ContrastSubFilter(contrast));
        Bitmap outputImageContrast = myFilterContrast.processFilter(mutableBitmap);

        contrastFilteredBitmap = outputImageContrast;

        return outputImageContrast;


    }
}
