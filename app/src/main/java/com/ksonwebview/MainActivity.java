package com.ksonwebview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void oneClick(View view){
        startActivity(new Intent(MainActivity.this, OneActivity.class));
    }

    public void twoClick(View view){
        startActivity(new Intent(MainActivity.this, TwoActivity.class));
    }

    public void threeClick(View view){
        startActivity(new Intent(MainActivity.this, ThreeActivity.class));
    }


}
