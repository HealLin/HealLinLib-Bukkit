package com.corelin.library.http;

import com.corelin.library.CoreLib;

import java.io.File;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/23 2:23
 * @版本 1.0
 */
public class DownloadModule {

    private CoreLibFileDownload download;

    public DownloadModule(CoreLib lib){
        download = new CoreLibFileDownload(lib);

    }

    public boolean downloadMainModule(){
      //  download.downloadFile("http://www.baidu.com" , new File("F:\\dd.gif"));
        return true;
    }
}
