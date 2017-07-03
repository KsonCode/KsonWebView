package com.ksonwebview;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by kson on 2017/7/3.
 */

public class AndroidJs extends Object{

    @JavascriptInterface
    public void hello(String msg){
        Log.e("hello--","js调用了android的hello方法");
    }
}
