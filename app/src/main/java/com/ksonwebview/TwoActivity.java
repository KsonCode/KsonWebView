package com.ksonwebview;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * 该方法比第一种方法效率更高、使用更简洁。
 * 因为该方法的执行不会使页面刷新，而第一种方法（loadUrl ）的执行则会。 Android 4.4 后才可使用
 */
public class TwoActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        webView = (WebView) findViewById(R.id.webview);

        initWebView();
    }

    private void initWebView() {
        WebSettings websetting = webView.getSettings();
        //设置与js交互权限
        websetting.setJavaScriptEnabled(true);
        //设置允许js弹窗
        websetting.setJavaScriptCanOpenWindowsAutomatically(true);
        //先载入JS代码  格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/javascripttwo.html");


        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                return super.onJsAlert(view, url, message, result);

                AlertDialog.Builder b = new AlertDialog.Builder(TwoActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });
    }


    public void click(View view) {

        webView.post(new Runnable() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
//                webView.loadUrl("javascript:callJS()");
                webView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(TwoActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
