package com.corelin.library.http;


import com.corelin.library.CoreLib;
import com.corelin.library.api.event.CoreLibFileDownloadEvent;
import com.corelin.library.http.interfaces.DownloadStatusListener;
import lombok.SneakyThrows;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 择忆霖心
 * @简述 TODO
 * @时间 2020/11/23 18:08
 * @版本 1.0
 */
public class CoreLibFileDownload {

    private CoreLib lib;

    public CoreLibFileDownload(CoreLib lib){
        this.lib = lib;
    }

    public void downloadFile(String downloadUrl , File file , DownloadStatusListener listener)  {
        //将事件提交到事件管理器，并且更新内容
        CoreLibFileDownloadEvent event = new CoreLibFileDownloadEvent(downloadUrl , file , listener);
        this.lib.getEventManager().postEvent(event);
        downloadUrl = event.getDownloadUrl();
        file = event.getFile();
        listener = event.getListener();
        try{
            listener.postDownload(downloadUrl , file);
            URL url = new URL(downloadUrl);
            //如果他是文件夹
            if (file.isDirectory()){
                //不会继续下载 , 开始执行下载结束
                return;
            }
            //如果上级文件夹不存在那么创建文件夹
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //如果文件不存在，那么创建文件
            if (!file.exists()){
                file.createNewFile();
            }
            //当前开始时间
            long tempTime = System.currentTimeMillis();
            long startTime = System.currentTimeMillis();
            //打开链接
            URLConnection urlConnection = url.openConnection();
            //从网络上拿到数据流并且缓冲输入流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            int current = 0;
            int size = 0;
            double tempProgress = 0.0;
            int tempSize = 0;
            int maxProgress = urlConnection.getContentLength() /100;
            //创建文件输出流,随时写出
            listener.startDownload(urlConnection);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] data = new byte[1024];
            while ((current = bufferedInputStream.read(data)) != -1) {
                //保存进度
                size += current;
                long currentTime = System.currentTimeMillis();
                int second = (int) ((currentTime - tempTime) / 1000);
                if (second >= 1){
                    double progress = (double) size / maxProgress;
                    tempTime = System.currentTimeMillis();
                    int speed = size - tempSize;
                    if (progress >= 1 && progress > tempProgress){
                        tempSize = size;
                        tempProgress = progress;
                        //更新进度和速度
                        listener.updateProgress(progress , speed);
                    }
                }
                fileOutputStream.write(data , 0 , current);
                //更新进度
                listener.updateSize(size);
            }
            long endTime = System.currentTimeMillis();
            fileOutputStream.flush();
            fileOutputStream.close();
            int adaptSize = size / 1024;
            if (adaptSize >= 1024){
                adaptSize = adaptSize /1024;
            }
            listener.downloadSuccess((endTime - startTime) , adaptSize);
        }catch (IOException e){
            listener.downloadFail(e);
        }
    }

}
