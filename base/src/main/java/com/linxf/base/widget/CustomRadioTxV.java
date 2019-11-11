package com.linxf.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.linxf.base.R;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.StringUtil;

import java.util.List;

/**
 * 类说明
 * Created by Linxf on 2019/8/8.
 */

public class CustomRadioTxV extends LinearLayout{

    private int textSize;
    private int textPaddingLeft;
    private int textPaddingTop;
    private int unselectedColor;
    private int selectedColor;
    private boolean cornerRound;
    private Drawable selectedLeftDrawable;
    private Drawable unselectedLeftDrawable;
    private Drawable selectedrightDrawable;
    private Drawable unselectedrightDrawable;
    private Drawable selectedCenterDrawable;
    private Drawable unselectedCenterDrawable;
    private OnTagSelectedListener onTagSelectedListener;

    private List<String> list;

    public CustomRadioTxV(Context context) {
        this(context,null);

    }

    public CustomRadioTxV(Context context, AttributeSet attributeSet) {
        this(context,attributeSet,0);

    }

    public void setOnSelectedListener(OnTagSelectedListener onTagSelectedListener){
        this.onTagSelectedListener = onTagSelectedListener;
    }

    public CustomRadioTxV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomRadioTxV, defStyleAttr, 0);
        textSize = a.getDimensionPixelSize(R.styleable.CustomRadioTxV_textSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));
        textPaddingLeft = a.getDimensionPixelSize(R.styleable.CustomRadioTxV_textPaddingLeft,  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        textPaddingTop = a.getDimensionPixelSize(R.styleable.CustomRadioTxV_textPaddingLeft,  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));

        unselectedColor = a.getColor(R.styleable.CustomRadioTxV_unselectedColor, Color.BLACK);
        selectedColor = a.getColor(R.styleable.CustomRadioTxV_selectedColor, Color.BLACK);
        cornerRound = a.getBoolean(R.styleable.CustomRadioTxV_cornerRound, false);
        selectedLeftDrawable = a.getDrawable(R.styleable.CustomRadioTxV_selectedleftBg);
        unselectedLeftDrawable = a.getDrawable(R.styleable.CustomRadioTxV_unselectedleftBg);
        selectedrightDrawable= a.getDrawable(R.styleable.CustomRadioTxV_selectedrightBg);
        unselectedrightDrawable = a.getDrawable(R.styleable.CustomRadioTxV_unselectedrightBg);
        selectedCenterDrawable= a.getDrawable(R.styleable.CustomRadioTxV_selectedcenterBg);
        unselectedCenterDrawable = a.getDrawable(R.styleable.CustomRadioTxV_unselectedcenterBg);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public List<String> getList(){
        return list;
    }

    public void setDatas(List<String> list,int selectedPos){
        if(StringUtil.isEmpty(list))
            return;

        this.list = list;


        RadioGroup radioGroup = new RadioGroup(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        radioGroup.setLayoutParams(layoutParams);

        radioGroup.setOrientation(HORIZONTAL);
        radioGroup.setGravity(Gravity.CENTER);




        for(int i=0;i<list.size();i++){
            String text = list.get(i);
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT));
            radioButton.setButtonDrawable(null);
            radioButton.setText(text);
            radioButton.setTextSize( TypedValue.COMPLEX_UNIT_PX,textSize);
            radioButton.setTextColor(unselectedColor);
            radioButton.setPadding(textPaddingLeft,textPaddingTop,textPaddingLeft,textPaddingTop);
            radioButton.setTag(i);
            radioButton.setOnCheckedChangeListener(listener);

            radioButton.setBackground(getDrawableByPos(0,i));
            radioGroup.addView(radioButton);

            if(i == selectedPos){
                radioButton.setChecked(true);

            } else {
                radioButton.setChecked(false);
            }

        }

        addView(radioGroup);


    }

    /**
     *
     * @param type 0未选择 1选中
     * @param pos
     * @return
     */
    private Drawable getDrawableByPos(int type,int pos){
        if(StringUtil.isEmpty(getList()) || type<0 || pos <0)
            return null;
        Drawable drawable = null;
        if(type == 0){
            if(pos == 0){
                //第一个
                if(unselectedLeftDrawable != null){
                    drawable = unselectedLeftDrawable;
                } else if(unselectedCenterDrawable != null){
                    drawable = unselectedCenterDrawable;
                }
            } else if(pos == getList().size() - 1){
                //最后一个
                if(unselectedrightDrawable != null){
                    drawable = unselectedrightDrawable;
                } else if(unselectedCenterDrawable != null){
                    drawable = unselectedCenterDrawable;
                }
            } else {
                //中间
                if(unselectedCenterDrawable != null){
                    drawable = unselectedCenterDrawable;
                }
            }

        } else {
            if(pos == 0){
                //第一个
                if(selectedLeftDrawable != null){
                    drawable = selectedLeftDrawable;
                } else if(selectedCenterDrawable != null){
                    drawable = selectedCenterDrawable;
                }
            } else if(pos == getList().size() - 1){
                //最后一个
                if(selectedrightDrawable != null){
                    drawable = selectedrightDrawable;
                } else if(selectedCenterDrawable != null){
                    drawable = selectedCenterDrawable;
                }
            } else {
                //中间
                if(selectedCenterDrawable != null){
                    drawable =selectedCenterDrawable;
                }
            }
        }
        return drawable;
    }


     CompoundButton.OnCheckedChangeListener listener =   new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                if(onTagSelectedListener != null){
                    onTagSelectedListener.onSelected((int)buttonView.getTag());
                }
                buttonView.setBackground(getDrawableByPos(1,(int)buttonView.getTag()));
                buttonView.setTextColor(selectedColor);
            } else {
                buttonView.setBackground(getDrawableByPos(0,(int)buttonView.getTag()));
                buttonView.setTextColor(unselectedColor);
            }

        }

    };
    private void initView(){


    }


    public interface OnTagSelectedListener{
        void onSelected(int position);
    }

}
