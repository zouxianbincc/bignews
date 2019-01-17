package com.example.zouxianbin.news;

public class BigNews {

    static {
        System.loadLibrary("native-lib");

    }

    public synchronized static native boolean bspatch(String oldFilePath, String patchFilePath, String newFilePath);
}
