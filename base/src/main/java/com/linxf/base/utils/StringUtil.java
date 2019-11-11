package com.linxf.base.utils;

import android.content.Context;
import android.text.TextUtils;



import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类说明
 * Created by Linxf on 2019/2/19.
 */

public class StringUtil {


    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }


    public static boolean isEmpty(Collection collection){
        return (collection == null || collection.isEmpty());
    }
    public static boolean isEmpty(String[] array){
        return (array == null || array.length == 0);
    }
    private static boolean isNotEmpty(String[] array){
        return !isEmpty(array);
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     * @param text
     * @return
     */
    public static boolean isEmpty(String text){
        return TextUtils.isEmpty(text)  || "null".equalsIgnoreCase(text);
    }
    /**
     * 判断字符串是否不为空
     * @param text
     * @return
     */
    public static boolean isNotEmpty(String text){
        return !isEmpty(text);
    }





    /**
     * 获取assete内容
     * @param context
     * @param path
     * @return
     */
    public static String getAssetsContent(Context context, String path){
        String text = null;
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(path);
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            text = new String(buffer, "UTF-8");

        } catch (Exception e) {

        }
        return text;
    }

    /**
     * 处理资源文件名称
     * @param name
     * @return
     */
    public static String getResouceName(String name){
        if(isNotEmpty(name)){
            return name.replaceAll(".png","").replaceAll("-","_");
        } else {
            return name;
        }
    }








    public static String toMD5(String text) throws NoSuchAlgorithmException {
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString();
    }


    public static List<String> convertStrToList(String text){
        List<String> list = new ArrayList<String>();
        if(isNotEmpty(text)){
            String[] st = text.split(",");
            for(int i=0;i<st.length;i++){
                list.add(st[i]);
            }
        }
        return list;
    }


}
