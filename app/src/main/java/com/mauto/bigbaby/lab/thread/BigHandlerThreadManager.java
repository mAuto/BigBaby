package com.mauto.bigbaby.lab.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

/**
 * Created by haohuidong on 19-6-17.
 */

/**
 * 这是一个很诡异的问题。
 * 在给TF做ANR处理，删除SmsReceiverService的时候，需要把内部逻辑提取出来做成一个单例封装起来单独使用。
 * 但是这个单例中的HandlerThread中的Handler无论如何都不能接收到发送的消息。
 * 在这里做的测试表明，在单例中HandlerTread是完全可以使用的。这个问题还需要深入解决，影响着TF和CF相当一部分的ANR。
 * */

public class BigHandlerThreadManager {
    //region >>> 单例
    /*
    * 19-6-17 下午12:04
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private BigHandlerThreadManager(){
        HandlerThread thread = new HandlerThread("sms executor thread", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ExecuteHandler(mServiceLooper);
    }

    private static class InstanceHolder{
        private static BigHandlerThreadManager sInstance = new BigHandlerThreadManager();
    }

    public static BigHandlerThreadManager manager(){
        return InstanceHolder.sInstance;
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////

    //region >>> HandlerThread
    /*
    * 19-6-17 下午12:08
    * */
    /////////////////////////////////////↓↓↓↓↓↓↓↓↓/////////////////////////////////////
    private final class ExecuteHandler extends Handler {
        public ExecuteHandler(Looper looper) {
            super(looper);
        }

        /**
         * Handle incoming transaction requests.
         * The incoming requests are initiated by the MMSC Server or by the MMS Client itself.
         */
        @Override
        public void handleMessage(Message msg) {
            String flag = (String) msg.obj;

            Log.e(">>> ExecuteHandler", "handleMessage@60 --> " + " " + flag);
        }
    }

    private ExecuteHandler mServiceHandler;
    private Looper mServiceLooper;

    public void enqueue(String flag) {
        Message msg = mServiceHandler.obtainMessage();
        msg.what = 0;
        msg.obj = flag;
        mServiceHandler.sendMessage(msg);
    }
    /////////////////////////////////////↑↑↑↑↑↑↑↑↑/////////////////////////////////////


}
