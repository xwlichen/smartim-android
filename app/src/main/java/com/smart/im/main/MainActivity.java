package com.smart.im.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        TextView text = findViewById(R.id.tv);
//        text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CCResult result = null;
//                CC cc = null;
////                Utils.navigation(MainActivity.this, RouterHub.ZHIHU_HOMEACTIVITY);
//                cc = CC.obtainBuilder("module_login.login")
//                        .setActionName("showActivityA")
//                        .build();
//                result = cc.call();
//
//            }
//        });
    }
}
