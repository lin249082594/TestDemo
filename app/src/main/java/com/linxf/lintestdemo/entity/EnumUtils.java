package com.linxf.lintestdemo.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class EnumUtils {
    public static final int STRING_4G = 0;
    public static final int STRING_3G = 1;
    public static final int STRING_2G = 2;
    public static final int STRING_WIFI = 3;
    public static final int STRING_UNKNOWN = 4;

    @IntDef({STRING_4G,STRING_3G,STRING_2G,STRING_WIFI,STRING_UNKNOWN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetWorkStatus{}

    public static String getNetWorkString(@NetWorkStatus int type) {
        if (type == STRING_4G) {
            return "NETWORK_4G";
        } else if (type == STRING_3G) {
            return "NETWORK_3G";
        } else if (type == STRING_2G) {
            return "NETWORK_2G";
        } else if (type == STRING_WIFI) {
            return "NETWORK_wifi";
        } else {
            return "unknown";
        }
    }


}
