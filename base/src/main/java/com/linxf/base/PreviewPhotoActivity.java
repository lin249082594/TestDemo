package com.linxf.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;


import com.linxf.base.utils.ImageLoader;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.widget.pic.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看图片
 */
public class PreviewPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> urlList = new ArrayList<String>();
    private ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(0);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        final int i = getIntent().getIntExtra("i", 0);
        urlList = getIntent().getStringArrayListExtra("picList");
        setContentView(R.layout.activity_preview);
        imageLoader = new ImageLoader(PreviewPhotoActivity.this);
        ViewPager previewPager = (ViewPager) findViewById(R.id.preview);
        previewPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 10));
        previewPager.setAdapter(new PagerAdapter() {
            @Override
            public void finishUpdate(@NonNull ViewGroup container) {
                super.finishUpdate(container);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startPostponedEnterTransition();
                }
            }

            @Override
            public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.setPrimaryItem(container, position, object);
                int count = container.getChildCount();
                View child;
                for (int j = 0; j < count; j++) {
                    child = container.getChildAt(j);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (child == object) {
                            ((View)object).setTransitionName("profile" + i);
                        } else {
                            child.setTransitionName(null);
                        }
                    }
                }
            }

            @Override
            public int getCount() {
                return urlList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                final PhotoView photoView = new PhotoView(container.getContext());
                photoView.enable();
                photoView.setOnClickListener(PreviewPhotoActivity.this);
                int img = 0;

                LogUtil.error("url:" + urlList.get(position));

                imageLoader.loadImage(urlList.get(position),photoView);

                container.addView(photoView);
                return photoView;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        previewPager.setCurrentItem(i, false);
    }

    @Override
    public void onClick(View v) {

        onBackPressed();
    }


    public static void jumpToPreviewPhoto(Context context,View view,int position, List<String> images){
        String name = "profile";
        Intent intent = new Intent(context, PreviewPhotoActivity.class);
        intent.putExtra("i", position);
        intent.putExtra("picList", (ArrayList)images);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP && context instanceof Activity && view != null) {
            view.setTransitionName(name);

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, view, name);
            context.startActivity(intent, options.toBundle());

        } else {

            context.startActivity(intent);
        }
    }

}
