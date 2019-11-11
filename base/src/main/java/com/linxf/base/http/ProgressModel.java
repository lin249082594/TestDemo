package com.linxf.base.http;


/**
 * http请求累封装
 * Created by Linxf on 2018/10/9.
 */

public class ProgressModel {
    private long currentBytes;
    private long contentLength;
    private boolean done;

    public ProgressModel(long currentBytes,long contentLength,boolean done){
        this.currentBytes = currentBytes;
        this.contentLength = contentLength;
        this.done = done;
    }

    public long getCurrentBytes() {
        return currentBytes;
    }

    public void setCurrentBytes(long currentBytes) {
        this.currentBytes = currentBytes;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
