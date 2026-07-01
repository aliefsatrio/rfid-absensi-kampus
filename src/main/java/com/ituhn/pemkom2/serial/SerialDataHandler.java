package com.ituhn.pemkom2.serial;

public interface SerialDataHandler<T> {
    void onDataReceived(T data);
}