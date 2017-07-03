package com.ksonwebview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ksonwebview.R;

/**
 * 点击Android按钮，即调用WebView JS（文本名为javascript）中callJS（）
 */
public class OneActivity extends AppCompatActivity {
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
        webView.loadUrl("file:///android_asset/javascriptone.html");


        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
//                return super.onJsAlert(view, url, message, result);

                AlertDialog.Builder b = new AlertDialog.Builder(OneActivity.this);
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
            @Override
            public void run() {
                webView.loadUrl("javascript:callJS()");
            }
        });

    }
}
