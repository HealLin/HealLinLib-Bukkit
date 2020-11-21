package com.corelin.library.http.interfaces;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/24 15:29
 * @版本 1.0
 */
public interface DownloadStatusListener {

    /**
     * 即将开始下载，允许用户更改下载地址和文件地址
     * @param downloadUrl
     * @param file
     */
    void postDownload(String downloadUrl, File file);

    void startDownload(URLConnection urlConnection);


    void updateSize(int size);

    void updateProgress(double progress , int speed);

    void downloadSuccess(long useTime, int size);

    void downloadFail(IOException exception);
}
