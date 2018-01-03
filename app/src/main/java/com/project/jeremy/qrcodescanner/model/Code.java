package com.project.jeremy.qrcodescanner.model;


import java.util.concurrent.atomic.AtomicInteger;

public class Code {

    private String data;
    private AtomicInteger count;

    public Code(String data, AtomicInteger count) {
        this.data = data;
        this.count = count;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}
