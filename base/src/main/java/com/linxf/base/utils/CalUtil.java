package com.linxf.base.utils;

import java.math.BigDecimal;

/**
 * 计算工具类
 * Created by Linxf on 2019/3/27.
 */

public class CalUtil {
    /**
     * 分转换成元
     * @param fen
     * @return
     */
    public static String fen2Yuan(String fen){

        try {
            String yuan = "";
            BigDecimal fenPrince = new BigDecimal(fen);
            BigDecimal[] result = fenPrince.divideAndRemainder(new BigDecimal(100));
            if(result.length == 2){
                if(result[1].compareTo(new BigDecimal(10)) < 0){
                    yuan = result[0] + ".0" + result[1];
                }else {
                    yuan = result[0] + "." + result[1];
                }
            }
            return removeAmountEnd(yuan);
        } catch (Exception e){
            return fen;
        }

    }
    /**
     * 去除金额尾数
     * @param str
     * @return
     */
    public static String removeAmountEnd(String str){
        if(StringUtil.isNotEmpty(str)){
            if(str.indexOf(".") > 0){
                str = str.replaceAll("0+?$", "");//去掉多余的0
                str = str.replaceAll("[.]$", "");//如最后一位是.则去掉
            }
        }

        return str;
    }

    /**
     * 比较价格
     * @param amount1
     * @param amount2
     * @return
     */
    public static int compareStrPrice(String amount1,String amount2){
        int flag = 0;
        try {
            LogUtil.error("amount1:" + amount1);
            LogUtil.error("amount2:" + amount2);
            BigDecimal b1 = new BigDecimal(amount1);
            BigDecimal b2 = new BigDecimal(amount2);
            flag = b1.compareTo(b2);
        } catch ( Exception e){

        }
        return  flag;
    }
    /**
     * 获取相加的价格
     * @param amount1
     * @param amount2
     * @return
     */
    public static String getAddAmount(String amount1,String amount2,int scale){
        String amount = "";
        if(StringUtil.isNotEmpty(amount1) && StringUtil.isNotEmpty( amount2)){
            BigDecimal b1 = new BigDecimal(amount1);
            BigDecimal b2 = new BigDecimal(amount2);
            b1 = b1.add(b2);
            amount = b1.setScale(scale,BigDecimal.ROUND_UP).toString();
        }
        return amount;
    }

    /**
     * 两数相减
     * @param amount1
     * @param amount2
     * @param scale
     * @return
     */
    public static String getMinusAmount(String amount1,String amount2,int scale){
        String amount = "";
        if(StringUtil.isNotEmpty(amount1)){
            amount = amount1;
            if(StringUtil.isNotEmpty( amount2)){
                BigDecimal b1 = new BigDecimal(amount1);
                BigDecimal b2 = new BigDecimal(amount2);
                b1 = b1.subtract(b2);
                amount = b1.setScale(scale,BigDecimal.ROUND_UP).toString();
            }
        }

        return amount;
    }
}
