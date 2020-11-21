package com.corelin.library.api.event;

import com.corelin.library.http.interfaces.DownloadStatusListener;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
    *@简述 TODO
    *@author 择忆霖心
    *@时间 2020/11/24 15:45
    *@版本 1.0
    */
public class CoreLibFileDownloadEvent extends CoreLibEvent{

    @Setter
    @Getter
    private String downloadUrl;

    @Setter
    @Getter
    private File file;

    @Setter
    @Getter
    private DownloadStatusListener listener;

    public CoreLibFileDownloadEvent(String downloadUrl, File file, DownloadStatusListener listener) {
        this.downloadUrl = downloadUrl;
        this.file = file;
        this.listener = listener;
    }


}
