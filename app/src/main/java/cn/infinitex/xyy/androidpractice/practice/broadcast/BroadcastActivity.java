package cn.infinitex.xyy.androidpractice.practice.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.infinitex.xyy.androidpractice.R;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        findViewById(R.id.btn_send).setOnClickListener((v) -> {
            Intent intent = new Intent("cn.infinitex.simplereceiver");
            intent.putExtra("number", 100);
            sendBroadcast(intent);
        });
    }
}
