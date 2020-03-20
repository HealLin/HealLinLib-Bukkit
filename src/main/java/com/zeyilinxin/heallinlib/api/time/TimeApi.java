package com.zeyilinxin.heallinlib.api.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeApi {

    public static String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        return df.format(new Date());
    }
}
