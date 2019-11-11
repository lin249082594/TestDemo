package com.linxf.base.http;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linxf.base.utils.LogUtil;
import com.linxf.base.utils.StringUtil;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http请求累封装
 * Created by Linxf on 2018/10/9.
 */
public class HttpTask{

    public static final String TAG = "HttpTask";

    /**
     * 请求任务id
     */
    private int httpTaskId;

    /**
     * 监听回调
     */
    private Callback callback;

    private static int timeout = HttpConfig.HTTP_TIME_OUT;


    private int id;

    private HttpTaskListener listener;

    private JSONObject param ;

    public HttpTask(int id, HttpTaskListener listener,JSONObject param) {
        super();
        this.id = id;
        this.listener = listener;
        this.param = param;
    }

    /**
     * okhttp上传文件
     * @param url
     * @param file
     */
    public void httpPostFile(String url,File file){
        try {
            if(file == null || !file.exists()){
                LogUtil.error(TAG,"httpPostfile 文件不存在" );
                return;
            } else {


                Request.Builder build = new Request.Builder();

                LogUtil.error(TAG,"http post --- send url:" + url);

                RequestBody fb = parseFileParam(file,param);


                LogUtil.error(TAG,"http post --- send data:"+ param);
                OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                Request request = null;
                request = build
                        .url(url)
//                    .post(RequestBody.create(MediaType.parse(Http.JSON_TYPE), data))
                        .post(fb)
                        .build();
                Call call = mOkHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.error(TAG,"http post --- return onFailure:" + e.toString());
                        listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){

                            String result =  response.body().string();
                            LogUtil.error(TAG,"http post --- return:" + result);
                            listener.onSucess(id,param,result);


                        } else {
                            LogUtil.error(TAG,"http post --- return fail:" + response.code());
                            listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);
                        }
                    }
                });
            }
        } catch (Exception e){
            LogUtil.error(TAG,"httpPost请求异常:" + e.toString());
        }
    }

    /**
     * 同步上传文件
     * @param url
     * @param file
     */
    public String httpPostFileAsync(String url,File file){
        String result = "";
        try {

            if(file == null || !file.exists()){
                LogUtil.error(TAG,"httpPostfile 文件不存在" );
                return "";
            } else {


                Request.Builder build = new Request.Builder();


                LogUtil.error(TAG,"http post --- send url:" + url);

                RequestBody fb = parseFileParam(file,param);


                LogUtil.error(TAG,"http post --- send data:"+ param);
                OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                Request request = null;
                request = build
                        .url(url)
//                    .post(RequestBody.create(MediaType.parse(Http.JSON_TYPE), data))
                        .post(fb)
                        .build();
                Call call = mOkHttpClient.newCall(request);

                Response response = call.execute();
                if(response != null && response.isSuccessful() && response.body() != null){
                    result = response.body().string();
                }

            }
        } catch (Exception e){
            LogUtil.error(TAG,"httpPost请求异常:" + e.toString());
        }
        LogUtil.error("同步上传图片:" + result);
        return result;
    }

    public static OkHttpClient getUnsafeOkHttpClient() {

        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);

            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void httpGetOnline(String url){
        try {
            //统一添加参数  数据版本号跟客户端类型
//            data.put("ver",Http.HTTP_VER);
//            data.put("dev",Http.HTTP_DEV);




            Request.Builder build = new Request.Builder();
            RequestBody fb = null;


            if(param != null && param.getJSONArray(HttpConfig.HTTP_HEADER_TAG) != null){
                //如果有header数据
                JSONArray headers = param.getJSONArray(HttpConfig.HTTP_HEADER_TAG);
                if(headers != null && headers.size() > 0){
                    for(int i=0;i<headers.size();i++){
                        String name = (String) headers.get(i);
                        if(StringUtil.isNotEmpty(name) && name.contains(":")){
                            String headerName = name.split(":")[0];
                            String value = name.split(":")[1];
                            build.addHeader(headerName,value);

                        }
                    }
                }
                param.remove(HttpConfig.HTTP_HEADER_TAG);
            }
            if(param != null ){
                StringBuilder params = new StringBuilder();
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    params.append(entry.getKey() + "=" + entry.getValue().toString());
                }
                if(params.length() > 1){
                    url += "?" + params.toString();
                }
            }
            LogUtil.error(TAG,"http get --- send url:" + url);

//            LogUtil.error(TAG,"http post --- send RequestBody:"+ fb.);

            LogUtil.error(TAG,"http get --- send data:"+ param);
            OkHttpClient mOkHttpClient = getUnsafeOkHttpClient();

            Request request = null;
            request = build
                    .url(url)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.error(TAG,"http get --- return onFailure:" + e.toString());
                    listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result =  response.body().string();
                    LogUtil.error(TAG,"http get --- return:" + result);
                    listener.onSucess(id,param,result);
                }
            });
        } catch (Exception e){
            LogUtil.error(TAG,"httpGet请求异常:" + e.toString());
        }
    }

    /**
     * Http get请求
     * @param url
     */
    public void httpGetNoCheck(String url){
        try {
            //统一添加参数  数据版本号跟客户端类型
//            data.put("ver",Http.HTTP_VER);
//            data.put("dev",Http.HTTP_DEV);




            Request.Builder build = new Request.Builder();
            RequestBody fb = null;


            if(param != null && param.getJSONArray(HttpConfig.HTTP_HEADER_TAG) != null){
                //如果有header数据
                JSONArray headers = param.getJSONArray(HttpConfig.HTTP_HEADER_TAG);
                if(headers != null && headers.size() > 0){
                    for(int i=0;i<headers.size();i++){
                        String name = (String) headers.get(i);
                        if(StringUtil.isNotEmpty(name) && name.contains(":")){
                            String headerName = name.split(":")[0];
                            String value = name.split(":")[1];
                            build.addHeader(headerName,value);

                        }
                    }
                }
                param.remove(HttpConfig.HTTP_HEADER_TAG);
            }
            if(param != null ){
                StringBuilder params = new StringBuilder();
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    params.append(entry.getKey() + "=" + entry.getValue().toString());
                }
                if(params.length() > 1){
                    url += "?" + params.toString();
                }
            }
            LogUtil.error(TAG,"http get --- send url:" + url);

//            LogUtil.error(TAG,"http post --- send RequestBody:"+ fb.);

            LogUtil.error(TAG,"http get --- send data:"+ param);
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            Request request = null;
            request = build
                    .url(url)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.error(TAG,"http get --- return onFailure:" + e.toString());
                    listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result =  response.body().string();
                    LogUtil.error(TAG,"http get --- return:" + result);
                   listener.onSucess(id,param,result);
                }
            });
        } catch (Exception e){
            LogUtil.error(TAG,"httpGet请求异常:" + e.toString());
        }
    }

    /**
     * Http get请求
     * @param url
     */
    public void httpGet(String url){
        try {
            //统一添加参数  数据版本号跟客户端类型
//            data.put("ver",Http.HTTP_VER);
//            data.put("dev",Http.HTTP_DEV);




            Request.Builder build = new Request.Builder();
            RequestBody fb = null;


            if(param != null && param.getJSONArray(HttpConfig.HTTP_HEADER_TAG) != null){
                //如果有header数据
                JSONArray headers = param.getJSONArray(HttpConfig.HTTP_HEADER_TAG);
                if(headers != null && headers.size() > 0){
                    for(int i=0;i<headers.size();i++){
                        String name = (String) headers.get(i);
                        if(StringUtil.isNotEmpty(name) && name.contains(":")){
                            String headerName = name.split(":")[0];
                            String value = name.split(":")[1];
                            build.addHeader(headerName,value);

                        }
                    }
                }
                param.remove(HttpConfig.HTTP_HEADER_TAG);
            }
            if(param != null ){
                StringBuilder params = new StringBuilder();
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    params.append(entry.getKey() + "=" + entry.getValue().toString());
                }
                if(params.length() > 1){
                    url += "?" + params.toString();
                }
            }
            LogUtil.error(TAG,"http get --- send url:" + url);

//            LogUtil.error(TAG,"http post --- send RequestBody:"+ fb.);

            LogUtil.error(TAG,"http get --- send data:"+ param);
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            Request request = null;
            request = build
                    .url(url)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.error(TAG,"http get --- return onFailure:" + e.toString());
                    listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result =  response.body().string();
                    LogUtil.error(TAG,"http get --- return:" + result);
                    checkResult(result,listener,id);
                }
            });
        } catch (Exception e){
            LogUtil.error(TAG,"httpGet请求异常:" + e.toString());
        }
    }

    public void httpPut(String url){
        try {
            //统一添加参数  数据版本号跟客户端类型
//            data.put("ver",Http.HTTP_VER);
//            data.put("dev",Http.HTTP_DEV);


            LogUtil.error(TAG,"http put --- send url:" + url);

            Request.Builder build = new Request.Builder();
            RequestBody fb = null;
            boolean isJson = false;

            if(param != null && param.getJSONArray(HttpConfig.HTTP_HEADER_TAG) != null){
                //如果有header数据
                JSONArray headers = param.getJSONArray(HttpConfig.HTTP_HEADER_TAG);
                if(headers != null && headers.size() > 0){
                    for(int i=0;i<headers.size();i++){
                        String name = (String) headers.get(i);
                        if(StringUtil.isNotEmpty(name) && name.contains(":")){
                            String headerName = name.split(":")[0];
                            String value = name.split(":")[1];
                            build.addHeader(headerName,value);
                            if(HttpConfig.CONTENT_TYPE.equals(headerName) && HttpConfig.CONTENT_TYPE_JSON.equals(value)){
                                isJson = true;
                            }
                        }
                    }
                }
                param.remove(HttpConfig.HTTP_HEADER_TAG);
            }

            if(isJson){
                fb = RequestBody.create(MediaType.parse(HttpConfig.CONTENT_TYPE_JSON), param.toString());
            } else {
                fb = getFrombodyByJson(param);
            }

//            LogUtil.error(TAG,"http post --- send RequestBody:"+ fb.);

            LogUtil.error(TAG,"http put --- send data:"+ param);
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            Request request = null;
            request = build
                    .url(url)
//                    .post(RequestBody.create(MediaType.parse(Http.JSON_TYPE), data))
                    .put(fb)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.error(TAG,"http put --- return onFailure:" + e.toString());
                    listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result =  response.body().string();
                    LogUtil.error(TAG,"http put --- return:" + result);
                    checkResult(result,listener,id);
                }
            });
        } catch (Exception e){
            LogUtil.error(TAG,"httpput请求异常:" + e.toString());
        }
    }


    /**
     * Http post请求
     * @param url
     */
    public void httpPost(String url){
        try {
            //统一添加参数  数据版本号跟客户端类型
//            data.put("ver",Http.HTTP_VER);
//            data.put("dev",Http.HTTP_DEV);


            LogUtil.error(TAG,"http post --- send url:" + url);

            Request.Builder build = new Request.Builder();
            RequestBody fb = null;
            boolean isJson = false;

            if(param != null && param.getJSONArray(HttpConfig.HTTP_HEADER_TAG) != null){
                //如果有header数据
                JSONArray headers = param.getJSONArray(HttpConfig.HTTP_HEADER_TAG);
                if(headers != null && headers.size() > 0){
                    for(int i=0;i<headers.size();i++){
                        String name = (String) headers.get(i);
                        if(StringUtil.isNotEmpty(name) && name.contains(":")){
                            String headerName = name.split(":")[0];
                            String value = name.split(":")[1];
                            build.addHeader(headerName,value);
                            if(HttpConfig.CONTENT_TYPE.equals(headerName) && HttpConfig.CONTENT_TYPE_JSON.equals(value)){
                                isJson = true;
                            }
                        }
                    }
                }
                param.remove(HttpConfig.HTTP_HEADER_TAG);
            }

            if(isJson){
                fb = RequestBody.create(MediaType.parse(HttpConfig.CONTENT_TYPE_JSON), param.toString());
            } else {
                fb = getFrombodyByJson(param);
            }

//            LogUtil.error(TAG,"http post --- send RequestBody:"+ fb.);

            LogUtil.error(TAG,"http post --- send data:"+ param);
            OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

            Request request = null;
            request = build
                    .url(url)
//                    .post(RequestBody.create(MediaType.parse(Http.JSON_TYPE), data))
                    .post(fb)
                    .build();
            Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    LogUtil.error(TAG,"http post --- return onFailure:" + e.toString());
                    listener.onException(id,param,HttpConfig.HTTP_INNER_ERROR_DESC);

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result =  response.body().string();
                    LogUtil.error(TAG,"http post --- return: code:" + response.code() + " body:" + result);
                    checkResult(result,listener,id);
                }
            });
        } catch (Exception e){
            LogUtil.error(TAG,"httpPost请求异常:" + e.toString());
        }
    }

    public void checkResult(String result,HttpTaskListener listener,int id){
        listener.onSucess(id,param,result);
//        if(TextUtils.isEmpty(result)){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("status","-1");
//            jsonObject.put("detail",HttpConfig.HTTP_INNER_ERROR);
//            result = jsonObject.toString();
//        }
//        JSONObject jsonObject = JSON.parseObject(result);
//
//        int state = jsonObject.getInteger("status");
//
//        if(state == HttpConfig.HTTP_SUCCESS_CODE){
//            //返回成功
//            listener.onSucess(id,result);
//        } else if( state == HttpConfig.HTTP_LOGOUT_CODE ){
//            //登出
//            listener.onException(id, StringUtil.isEmpty(jsonObject.getString("detail"))?HttpConfig.HTTP_INNER_ERROR_DESC:jsonObject.getString("detail"));
//            logout();
//        }else {
//            //返回失败
//            listener.onException(id, StringUtil.isEmpty(jsonObject.getString("detail"))?HttpConfig.HTTP_INNER_ERROR_DESC:jsonObject.getString("detail"));
//        }


    }

    public void logout(){
        Intent intent = new Intent();
        intent.setAction("LOGOUT_DEVICELOGOUTED");

    }

    public RequestBody getFrombodyByJson(JSONObject json) {
        FormBody.Builder bl = new FormBody.Builder();
        if(json != null) {
            try {

                for (Map.Entry<String, Object> entry : json.entrySet()) {
                    if(entry.getValue() != null){
                        bl.add(entry.getKey(),entry.getValue().toString());
                    }


                }

            } catch (Exception e){
                LogUtil.error(TAG,"fastjson转换参数异常:" + e.toString());
            }
        }

       return  bl.build();
    }

    public RequestBody parseFileParam(File file,JSONObject json) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody.Builder bl = new MultipartBody.Builder();
        if(json != null) {
            try {
                for (Map.Entry<String, Object> entry : json.entrySet()) {
                    bl.addFormDataPart(entry.getKey(),entry.getValue().toString());


                }
                bl.addFormDataPart("file", file.getName(), fileBody);

//            bl.addPart( Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\""+file.getName()+"\""), RequestBody.create(MediaType.parse("application/octet-stream"),file)
//            );



            } catch (Exception e){
                LogUtil.error(TAG,"fastjson转换参数异常:" + e.toString());
            }
        }

        return  bl.build();
    }
     private void onException(int id,String result){

         listener.onException(id,param,result);
     }

     public void onSuccess(int id,String result){
         listener.onSucess(id,param,result);
     }



    //下载文件方法
    public static void downloadFile(String url, final ProgressListener listener, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000,TimeUnit.MILLISECONDS).build();
        //增加拦截器
        OkHttpClient client = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ProgressResponseBody(response.body(),listener)).build();
            }
        }).build();

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    //下载文件方法
    public static void downloadFile(String url,int startIndex,int total, final ProgressListener listener, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000,TimeUnit.MILLISECONDS).build();
        //增加拦截器
        OkHttpClient client = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ProgressResponseBody(response.body(),listener)).build();
            }
        }).build();

        Request request = new Request.Builder().header("RANGE", "bytes=" + startIndex + "-" + total).url(url).build();
        client.newCall(request).enqueue(callback);
    }
}

