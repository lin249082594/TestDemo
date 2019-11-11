package com.linxf.base.constant;

/**
 * 类说明
 * Created by Linxf on 2019/3/27.
 */

public enum  ActivityAnim {
    EMPTY(0),
    TOP_TO_BOTTOM(1);//上下动画

    private int anim;
    ActivityAnim(int anim){
        this.anim = anim;
    }

    public static ActivityAnim getAnim(int anim){
        for(ActivityAnim activityAnim:values()){
            if(activityAnim.anim == anim){
                return activityAnim;
            }
        }
        return null;
    }

}
