package com.linxf.base.widget.datepicker;

import com.linxf.base.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 类说明
 * Created by Linxf on 2019/4/26.
 */

public class DateFormatUtils {

    public static final String DATE_FORMAT_PATTERN_YM = "yyyy-MM";
    public static final String DATE_FORMAT_PATTERN_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_PATTERN_YMD_HM = "yyyy-MM-dd HH:mm";



    public static String long2Str(long timestamp, String pattern) {
        return new SimpleDateFormat(pattern, Locale.CHINA).format(new Date(timestamp));
    }



    public static long str2Long(String dateStr, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.CHINA).parse(dateStr).getTime();
        } catch (Exception e) {
            LogUtil.error("异常:" + e.toString());
        }
        return 0;
    }

    private static String getFormatPattern(boolean showSpecificTime) {
        if (showSpecificTime) {
            return DATE_FORMAT_PATTERN_YMD_HM;
        } else {
            return DATE_FORMAT_PATTERN_YMD;
        }
    }

}
