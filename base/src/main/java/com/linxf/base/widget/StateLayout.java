package com.linxf.base.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxf.base.utils.LogUtil;

/**
 * 类说明
 * Created by Linxf on 2019/4/10.
 */

public class StateLayout extends FrameLayout implements StateShowInterface {

    private static Recorder recorder;

    boolean errorInflated = false, emptyInflated = false;

    private SparseArray<View> views = new SparseArray<>(4);

    public StateLayout(@NonNull Context context) {
        this(context, null);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void init(Recorder recorder1) {
        recorder = recorder1;
    }

    public StateLayout setContent(int contentres) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View contentView = inflater.inflate(contentres, this, false);
        views.put(StateShowInterface.CONTENT, contentView);
        addView(contentView);

        View loadingView = inflater.inflate(recorder.getLoadingres(), this, false);
        views.put(StateShowInterface.LOADING, loadingView);
        addView(loadingView);

        ViewStub emptyView = new ViewStub(getContext(), recorder.getEmptyres());
        views.put(StateShowInterface.EMPTY, emptyView);
        addView(emptyView);


        ViewStub errorView = new ViewStub(getContext(), recorder.getErrorres());
        addView(errorView);
        views.put(StateShowInterface.ERROR, errorView);

        showLoading();
        return this;
    }

    public View getContentView(){
        return views.get(StateShowInterface.CONTENT);
    }

    @Override
    public View showLoading() {
        views.get(StateShowInterface.LOADING).setVisibility(VISIBLE);
        views.get(StateShowInterface.CONTENT).setVisibility(GONE);
        views.get(StateShowInterface.EMPTY).setVisibility(GONE);
        views.get(StateShowInterface.ERROR).setVisibility(GONE);

        return  views.get(StateShowInterface.LOADING);
    }

    @Override
    public View showEmpty() {
        if (!emptyInflated) {
            emptyInflated = true;
            views.put(StateShowInterface.EMPTY, ((ViewStub) views.get(StateShowInterface.EMPTY)).inflate());
            views.get(StateShowInterface.EMPTY).setVisibility(VISIBLE);
        } else {
            views.get(StateShowInterface.EMPTY).setVisibility(VISIBLE);
        }
//        switchSource(text, buttonText, drawbleres, listener, StateShowInterface.EMPTY);


        views.get(StateShowInterface.LOADING).setVisibility(GONE);
        views.get(StateShowInterface.ERROR).setVisibility(GONE);
        views.get(StateShowInterface.CONTENT).setVisibility(GONE);

        return  views.get(StateShowInterface.EMPTY);
    }

    @Override
    public View showContent() {
        views.get(StateShowInterface.CONTENT).setVisibility(VISIBLE);
        views.get(StateShowInterface.LOADING).setVisibility(GONE);
        views.get(StateShowInterface.ERROR).setVisibility(GONE);
        views.get(StateShowInterface.EMPTY).setVisibility(GONE);
        return  views.get(StateShowInterface.CONTENT);
    }

    @Override
    public View showError() {
        if (!errorInflated) {
            errorInflated = true;
            views.put(StateShowInterface.ERROR, ((ViewStub) views.get(StateShowInterface.ERROR)).inflate());
            views.get(StateShowInterface.ERROR).setVisibility(VISIBLE);
        } else {
            views.get(StateShowInterface.ERROR).setVisibility(VISIBLE);
        }
//        switchSource(text, buttonText, drawbleres, listener, StateShowInterface.ERROR);

        views.get(StateShowInterface.LOADING).setVisibility(GONE);
        views.get(StateShowInterface.EMPTY).setVisibility(GONE);
        views.get(StateShowInterface.CONTENT).setVisibility(GONE);
        return    views.get(StateShowInterface.ERROR);
    }

    //    private void switchSource(String text, String buttonText, int drawbleres, OnClickListener listener, int type) {
//        //替换button文字
//        Button button = getButton(views.get(type));
//        LogUtil.error("showEmpty: " + (button != null));
//        if (button != null) {
//            button.setOnClickListener(listener);
//            if (!TextUtils.isEmpty(buttonText)) {
//                button.setText(buttonText);
//            }
//        }
//        //替换文字
//        if (!TextUtils.isEmpty(text)) {
//            TextView textview = getTextview(views.get(type));
//            if (textview != null) {
//                textview.setText(text);
//            }
//        }
//
//
//        //替换图片
//        if (drawbleres != 0) {
//            ImageView imageView = getImageView(views.get(type));
//            if (imageView != null)
//                imageView.setImageResource(drawbleres);
//        }
//    }

    public Button getButton(View view) {
        LogUtil.error("getButton: " + (view instanceof Button));

        if (view instanceof Button) {
            LogUtil.error( "getButton:返回了button ");

            return (Button) view;
        } else if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                Button button = getButton(((ViewGroup) view).getChildAt(i));
                if (button != null)
                    return button;

            }
        }
        LogUtil.error("getButton:返回了null");
        return null;
    }

    public TextView getTextview(View view) {
        if (view instanceof TextView && !(view instanceof Button)) {
            return (TextView) view;
        } else if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                TextView textview = getTextview(((ViewGroup) view).getChildAt(i));
                if (textview != null)
                    return textview;
            }
        }
        return null;
    }

    public ImageView getImageView(View view) {
        if (view instanceof ImageView) {

            return (ImageView) view;
        } else if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                ImageView imageView = getImageView(((ViewGroup) view).getChildAt(i));
                if (imageView != null)
                    return imageView;
            }
        }
        return null;
    }

    public static class Builder {
        private int Loadingres = 0;
        private int emptyres = 0;
        private int errorres = 0;

        public Builder setLoadingRes(int res) {
            this.Loadingres = res;
            return this;
        }

        public Builder setEmptyRes(int res) {
            this.emptyres = res;
            return this;
        }

        public Builder setErrorRes(int res) {
            this.errorres = res;
            return this;
        }

        public Recorder build() {
            Recorder recorder = new Recorder();
            recorder.setLoadingres(Loadingres);
            recorder.setEmptyres(emptyres);
            recorder.setErrorres(errorres);
            return recorder;
        }

    }


    public static class Recorder {
        private int Loadingres = 0;
        private int emptyres = 0;
        private int errorres = 0;

        private Recorder() {
        }

        private void setLoadingres(int loadingres) {
            Loadingres = loadingres;
        }

        private void setEmptyres(int emptyres) {
            this.emptyres = emptyres;
        }


        private void setErrorres(int errorres) {
            this.errorres = errorres;
        }

        private int getLoadingres() {
            return Loadingres;
        }

        private int getEmptyres() {
            return emptyres;
        }


        private int getErrorres() {
            return errorres;
        }

        @Override
        public String toString() {
            return "Recorder{" +
                    "Loadingres=" + Loadingres +
                    ", emptyres=" + emptyres +
                    ", contentres=" +
                    ", errorres=" + errorres +
                    '}';
        }
    }
}

interface StateShowInterface {
    int LOADING = 1, EMPTY = 2, CONTENT = 3, ERROR = 4;

    View showLoading();


    View showEmpty();

    View showContent();

    View showError();
}
