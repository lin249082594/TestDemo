package com.linxf.base.http;

/**
 * 类说明
 * Created by Linxf on 2019/2/19.
 */

public class HttpConfig {
    //
    public final static String HTTP_VER = "1.0";
    public final static String HTTP_DEV = "11";

    public final static String CONTENT_TYPE= "Content-Type";
    public final static String METHOD = "method";
    public final static String CONTENT_TYPE_JSON = "application/json";
    public final static String CONTENT_TYPE_FORM = "form/data";

    public final static String HTTP_HEADER_TAG= "_header";

    /**
     * http成功
     */
    public final static int HTTP_SUCCESS = 200;
    /**
     * 是否开启加密
     */
    public final static int HTTP_ENCODE = 1;
    /**
     * 网络请求超时时间
     */
    public final static int HTTP_TIME_OUT = 20 * 1000;

    /**
     * get请求方式
     */
    public final static String HTTP_TASK_GET = "get";

    /**
     * post请求方式
     */
    public final static String HTTP_TASK_POST = "get";

    /**
     * UTF-8
     */
    public final static String UTF_8 = "UTF-8";

    /**
     * GBK
     */
    public final static String GBK = "GBK";

    /**
     * SID
     */
    public final static String SID = "E7EE2BC8FF6C48C6D7BE7B94A836D106";

    /****************************** task error **********************************/

    /**
     * http :101请求参数错误
     */
    public final static int HTTP_PARAM_ERROR = 101;

    /**
     * http :102 请求内部错误
     */
    public final static int HTTP_INNER_ERROR = 102;

    /**
     * 请求参数描述
     */
    public final static String HTTP_PARAM_ERROR_DESC = "请求参数错误！";

    /**
     * 请求内部错误描述
     */
    public final static String HTTP_INNER_ERROR_DESC = "网络加载慢，请稍后再试！";

    /*************     服务器返回错误描述    **************/
    public final static String HTTP_RETUNRN_ERROR_DESC = "网络加载慢，请稍后再试！";


    /**
     * http请求成功code
     */
    public final static int HTTP_SUCCESS_CODE = 1;
    /**
     * 登录超市code
     */
    public final static int HTTP_LOGOUT_CODE = 401;

    public final static int HTTP_SUCCESS_SMSCODE = 10002;
    public final static int HTTP_SUCCESS_VERIFYSMSCODE = 10010;
    public final static int HTTP_SUCCESS_UPDATEPWDCODE = 10077;
    public final static int HTTP_SUCCESS_LOGIN = 10057;

}
