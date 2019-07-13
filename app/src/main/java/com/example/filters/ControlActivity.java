package com.example.filters;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.filters.utility.Helper;
import com.example.filters.utility.TransformImage;
import com.pepperonas.materialdialog.MaterialDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ControlActivity extends AppCompatActivity {

    static
    {
        System.loadLibrary("NativeImageProcessor");
    }

    Toolbar mControlToolbar;

    ImageView mCenterImageView;
    Uri mSelectedImageUri;
    TransformImage mTransformImage;

    int mScreenWidth;
    int mScreenHeight;

    int mCurrentFilter;
    SeekBar mSeekBar;
    ImageView mTickImageView;
    ImageView mCancelImageView;

    Target mApplySingleTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            int currentFilterValue = mSeekBar.getProgress();

            if(mCurrentFilter == TransformImage.FILTER_BRIGHTNESS)
            {
                mTransformImage.applyBrightnessSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_BRIGHTNESS),mTransformImage.getBitmap(TransformImage.FILTER_BRIGHTNESS));

                Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_BRIGHTNESS)));
                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_BRIGHTNESS))).resize(0,mScreenHeight/2).into(mCenterImageView);

            }
            else if (mCurrentFilter == TransformImage.FILTER_SATURATION)
            {
                mTransformImage.applySaturationSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_SATURATION),mTransformImage.getBitmap(TransformImage.FILTER_SATURATION));

                Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_SATURATION)));
                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_SATURATION))).resize(0,mScreenHeight/2).into(mCenterImageView);

            }
            else if (mCurrentFilter == TransformImage.FILTER_VIGNETTE)
            {
                mTransformImage.applyVignetteSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_VIGNETTE),mTransformImage.getBitmap(TransformImage.FILTER_VIGNETTE));

                Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_VIGNETTE)));
                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_VIGNETTE))).resize(0,mScreenHeight/2).into(mCenterImageView);

            }
            else if(mCurrentFilter == TransformImage.FILTER_CONTRAST)
            {
                mTransformImage.applyContrastSubFilter(currentFilterValue);

                Helper.writeDataIntoExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_CONTRAST),mTransformImage.getBitmap(TransformImage.FILTER_CONTRAST));

                Picasso.get().invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_CONTRAST)));
                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this,mTransformImage.fileName(TransformImage.FILTER_CONTRAST))).resize(0,mScreenHeight/2).into(mCenterImageView);

            }
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    Target mSmallTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            mTransformImage = new TransformImage(ControlActivity.this, bitmap);

            mTransformImage.applyBrightnessSubFilter(TransformImage.DEFAULT_BRIGHTNESS);

            String fileName = mTransformImage.fileName(TransformImage.FILTER_BRIGHTNESS);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_BRIGHTNESS), mTransformImage.getBitmap(mTransformImage.FILTER_BRIGHTNESS));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, fileName)).fit().centerInside().into(mFirstFilterPreviewImageView);

            //
            mTransformImage.applySaturationSubFilter(TransformImage.DEFAULT_SATURATION);

            String fileNameSaturation = mTransformImage.fileName(TransformImage.FILTER_SATURATION);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_SATURATION), mTransformImage.getBitmap(mTransformImage.FILTER_SATURATION));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, fileNameSaturation)).fit().centerInside().into(mSecondFilterPreviewImageView);

            //
            mTransformImage.applyVignetteSubFilter(TransformImage.DEFAULT_VIGNETTE);

            String filenameVignette = mTransformImage.fileName(TransformImage.FILTER_VIGNETTE);
            Helper.writeDataIntoExternalStorage(ControlActivity.this,filenameVignette, mTransformImage.getBitmap(mTransformImage.FILTER_VIGNETTE));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, filenameVignette)).fit().centerInside().into(mThirdFilterPreviewImageView);

            //
            mTransformImage.applyContrastSubFilter(TransformImage.DEFAULT_CONTRAST);

            String fileNameContrast = mTransformImage.fileName(TransformImage.FILTER_CONTRAST);
            Helper.writeDataIntoExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_CONTRAST), mTransformImage.getBitmap(mTransformImage.FILTER_CONTRAST));
            Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, fileNameContrast)).fit().centerInside().into(mFourthFilterPreviewImageView);


        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    final static int PICK_IMAGE = 2;
    final static int MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION = 3;
    private static final String TAG = ControlActivity.class.getSimpleName();

    ImageView mFirstFilterPreviewImageView;
    ImageView mSecondFilterPreviewImageView;
    ImageView mThirdFilterPreviewImageView;
    ImageView mFourthFilterPreviewImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        mSeekBar = (SeekBar) findViewById(R.id.simpleSeekBar);

        mControlToolbar = (Toolbar)findViewById(R.id.toolbar);
        mCenterImageView = (ImageView)findViewById(R.id.centerImage);

        mControlToolbar.setTitle(getString(R.string.app_name));
        mControlToolbar.setNavigationIcon(R.drawable.iconf);
        mControlToolbar.setTitleTextColor(getResources().getColor(R.color.colorText));

        mFirstFilterPreviewImageView = (ImageView)findViewById(R.id.imageView13);
        mSecondFilterPreviewImageView = (ImageView)findViewById(R.id.imageView14);
        mThirdFilterPreviewImageView = (ImageView)findViewById(R.id.imageView15);
        mFourthFilterPreviewImageView = (ImageView)findViewById(R.id.imageView16);


        mTickImageView = (ImageView) findViewById(R.id.imageView12);
        mTickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ControlActivity.this, ImagePreviewActivity.class);
                startActivity(intent);
            }
        });

        mCenterImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        //show user a message
                        new MaterialDialog.Builder(ControlActivity.this).title(R.string.permission_required)
                                .message(R.string.permission_required_message)
                                .negativeText(R.string.permission_negative_text)
                                .positiveText(R.string.permission_positive_text).canceledOnTouchOutside(true).show();
                    } else {
                        ActivityCompat.requestPermissions(ControlActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION);
                    }
                    return;

                }
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }
        });

        mFirstFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekBar.setMax(TransformImage.MAX_BRIGHTNESS);
                mSeekBar.setProgress(TransformImage.DEFAULT_BRIGHTNESS);
                mCurrentFilter = TransformImage.FILTER_BRIGHTNESS;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_BRIGHTNESS))).resize(0,mScreenHeight/2).into(mCenterImageView);
            }
        });

        mSecondFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekBar.setMax(TransformImage.MAX_SATURATION);
                mSeekBar.setProgress(TransformImage.DEFAULT_SATURATION);

                mCurrentFilter = TransformImage.FILTER_SATURATION;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_SATURATION))).resize(0,mScreenHeight/2).into(mCenterImageView);
            }
        });

        mThirdFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekBar.setMax(TransformImage.MAX_VIGNETTE);
                mSeekBar.setProgress(TransformImage.DEFAULT_VIGNETTE);

                mCurrentFilter = TransformImage.FILTER_VIGNETTE;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_VIGNETTE))).resize(0,mScreenHeight/2).into(mCenterImageView);
            }
        });

        mFourthFilterPreviewImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSeekBar.setMax(TransformImage.MAX_CONTRAST);
                mSeekBar.setProgress(TransformImage.DEFAULT_CONTRAST);

                mCurrentFilter = TransformImage.FILTER_CONTRAST;

                Picasso.get().load(Helper.getFileFromExternalStorage(ControlActivity.this, mTransformImage.fileName(TransformImage.FILTER_CONTRAST))).resize(0,mScreenHeight/2).into(mCenterImageView);
            }
        });

        mTickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.get().load(mSelectedImageUri).into(mApplySingleTarget);
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
    }

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch(requestCode)
        {
            case MY_PERMISSIONS_REQUEST_STORAGE_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //show message
                    new MaterialDialog.Builder(ControlActivity.this).title(R.string.permission_granted)
                            .message(R.string.permission_granted_message)
                            .positiveText(R.string.permissison_granted_positive).canceledOnTouchOutside(true).show();
                }
                else {
                    Log.d(TAG, "Permission Denied");
                }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE && resultCode == Activity.RESULT_OK){
            mSelectedImageUri = data.getData();

            Picasso.get().load(mSelectedImageUri).fit().centerInside().into(mCenterImageView);


        Picasso.get().load(mSelectedImageUri).into(mSmallTarget);


        }
    }
}
