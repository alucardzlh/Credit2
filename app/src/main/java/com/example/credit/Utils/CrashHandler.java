package com.example.credit.Utils;

import android.content.Context;

/**
 * Created by 章龙海 on 2016/12/22 17:49.
 *
 * @descript (自定义异常处理类)
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

   //需求是 整个应用程序 只有一个
    private  static  CrashHandler INSTANCE;
    private Context context;

    private CrashHandler(){

    }
    public static synchronized CrashHandler getInstance(){
        if(INSTANCE==null)
            INSTANCE=new CrashHandler();
        return INSTANCE;
    }
    public  void init(Context context){
        this.context=context;
    }



    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
