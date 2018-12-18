package com.mauto.bigbaby.support.router;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.mauto.bigbaby.support.markdown.MarkdownPointer;

/**
 * Created by haohuidong on 18-6-11.
 */

public class Router {

    /////////////////////////////////////////--> 18-6-11 下午3:25 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Singletone <-- ↓↓↓/////////////////////////////////////
    private Router(){};

    private static class InstanceHolder{
        public final static Router mInstance = new Router();
    }

    public static Router getInstance(){
        return InstanceHolder.mInstance;
    }
    /////////////////////////////////////↑↑↑ --> Singletone <-- ↑↑↑/////////////////////////////////////

    /////////////////////////////////////////--> 18-6-11 下午3:28 <--/////////////////////////////////////
    /////////////////////////////////////↓↓↓ --> Rout <-- ↓↓↓/////////////////////////////////////
    // jump without arguments
    public static void jump(Context from, RoutPointer pointer){
        jump(from, pointer, null);
    }

    // jump with arguments
    public static void jump(Context from, RoutPointer pointer, Bundle bundle){
        Intent intent = new Intent();
        String link = pointer.getTarget();
        if (link.contains("http")) {
            Uri uri = Uri.parse(MarkdownPointer.buildUri(link));
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.setClass(from, RouterMap.pickTarget(pointer));
            if (bundle != null)
                intent.putExtras(bundle);
            if (from instanceof Service){
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        }
        from.startActivity(intent);
    }
    /////////////////////////////////////↑↑↑ --> Rout <-- ↑↑↑/////////////////////////////////////
}
