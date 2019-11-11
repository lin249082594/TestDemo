package com.linxf.base.http;

/**
 * http请求累封装
 * Created by Linxf on 2018/10/9.
 */

public interface ProgressListener {
    //已完成的 总的文件长度 是否完成
    void onProgress(long currentBytes, long contentLength, boolean done);
}
