package com.linxf.base.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.linxf.base.application.GlideApp;
import com.linxf.base.utils.ImageLoader;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.VUtils;
import java.util.List;

import static android.R.attr.bitmap;


/**
 * 显示1~N张图片的View，自定义控件继承LinearLayout类
 * Created by Linxf on 2018/10/11
 */
public class MultiImageView extends LinearLayout {
    public  int MAX_WIDTH = 0;

    // 照片的Url列表
    private List<String> imagesList;

    /**
     * 长度 单位为Pixel
     **/
    private int pxOneWidth = VUtils.dip2px(getContext(),115);// 单张图时候的宽
    private int pxOneHeight = VUtils.dip2px(getContext(),150);// 单张图时候的高
    private int pxMoreWandH = 0;// 多张图的宽高
    private int pxImagePadding = VUtils.dip2px(getContext(),3);// 图片间的间距

    private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

    private LayoutParams onePicPara;
    private LayoutParams morePara;
    private LayoutParams rowPara;
    private OnItemClickListener mOnItemClickListener;

    private ImageLoader imageLoader;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MultiImageView(Context context) {
        super(context);

    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(List<String> lists) throws IllegalArgumentException {
        if (lists == null) {
            throw new IllegalArgumentException("imageList is null...");
        }
        imagesList = lists;

        if (MAX_WIDTH > 0) {
            pxMoreWandH = MAX_WIDTH / 3 - pxImagePadding;
            pxOneWidth = MAX_WIDTH / 2;
            pxOneHeight = MAX_WIDTH * 2 / 3;
            initImageLayoutParams();
        }

        initView();
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (MAX_WIDTH == 0) {
            int width = measureWidth(widthMeasureSpec);
            if (width > 0) {
                MAX_WIDTH = width;
                if (imagesList != null && imagesList.size() > 0) {
                    setList(imagesList);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private void initImageLayoutParams() {

        onePicPara = new LayoutParams(pxOneWidth, pxOneHeight);

        morePara = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara.setMargins(0, 0, pxImagePadding, 0);

        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;
        rowPara = new LayoutParams(match, wrap);
        rowPara.setMargins(0, 0, 0, pxImagePadding);
    }

    // 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
    private void initView() {
        imageLoader = new ImageLoader(getContext());
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if (MAX_WIDTH == 0) {
            //为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
            addView(new View(getContext()));
            return;
        }

        if (imagesList == null || imagesList.size() == 0) {
            return;
        }

        if (imagesList.size() == 1) {
            for (String url : imagesList) {
                final ImageView imageView = new ImageView(getContext());
                imageView.setId(url.hashCode());// 指定id
                imageView.setLayoutParams(onePicPara);
                imageView.setMinimumWidth(pxMoreWandH);
                imageView.setScaleType(ScaleType.CENTER_CROP);

                imageLoader.loadCornerCenterCropByTarget(url, 5, new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                        //设置iamgeView宽度
                        int width = drawable.getIntrinsicWidth();
                        int height = drawable.getIntrinsicHeight();
                        LogUtil.error("宽度：" + width + " 高度:" + height);
                        if(width > pxOneHeight || height > pxOneHeight){
                            //如果宽跟高有超过了
                            if(width > height){
                                //宽大于高
                                float sc = (float)pxOneHeight/width;
                                float mHeight = sc*height;
                                imageView.setLayoutParams(new LayoutParams(pxOneHeight,(int)mHeight));
                            } else if(width < height){
                                //高大于宽
                                float sc = (float)pxOneHeight/height;
                                float mWidth = sc*width;
                                imageView.setLayoutParams(new LayoutParams((int)mWidth,pxOneHeight));
                            } else {
                                imageView.setLayoutParams(new LayoutParams(pxOneHeight,pxOneHeight));
                            }
                        } else {
                            imageView.setLayoutParams(new LayoutParams(width,height));
                        }
                        imageView.setImageDrawable(drawable);
                    }


                });




                int position = 0;
                imageView.setTag(position);
                imageView.setOnClickListener(mImageViewOnClickListener);
                addView(imageView);
            }

        } else {
            int allCount = imagesList.size();
            if (allCount == 4) {
                MAX_PER_ROW_COUNT = 2;
            } else {
                MAX_PER_ROW_COUNT = 3;
            }
            int rowCount = allCount / MAX_PER_ROW_COUNT
                    + (allCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0);// 行数
            for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
                LinearLayout rowLayout = new LinearLayout(getContext());
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                rowLayout.setLayoutParams(rowPara);
                if (rowCursor == 0) {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0);
                }

                int columnCount = allCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT
                        : allCount % MAX_PER_ROW_COUNT;//每行的列数
                if (rowCursor != rowCount - 1) {
                    columnCount = MAX_PER_ROW_COUNT;
                }
                addView(rowLayout);

                int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
                for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
                    int position = columnCursor + rowOffset;
                    String thumbUrl = imagesList.get(position);

                    ImageView imageView = new ImageView(getContext());
                    imageView.setId(thumbUrl.hashCode());// 指定id
                    imageView.setLayoutParams(morePara);
                    imageView.setScaleType(ScaleType.CENTER_CROP);
//                    ImageLoader.getInstance().displayImage(thumbUrl, imageView);
//                    x.image().bind(imageView,thumbUrl);
                    try {
                        imageLoader.loadCornerCenterCropImage(thumbUrl,5,imageView);
                    } catch (Exception e){

                    }

                    imageView.setTag(position);
                    imageView.setOnClickListener(mImageViewOnClickListener);

                    rowLayout.addView(imageView);
                }
            }
        }
    }

    // 图片点击事件
    private View.OnClickListener mImageViewOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(MultiImageView.this, view, (Integer) view.getTag());
            }
        }
    };

    public interface OnItemClickListener {
        public void onItemClick(ViewGroup parent, View view, int position);
    }
}