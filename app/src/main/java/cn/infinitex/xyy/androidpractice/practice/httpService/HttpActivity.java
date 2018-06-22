package cn.infinitex.xyy.androidpractice.practice.httpService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.infinitex.xyy.androidpractice.R;

public class HttpActivity extends AppCompatActivity {
    private static final String TAG = "HttpService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        findViewById(R.id.btn_start).setOnClickListener((v) -> {
            Intent intent = new Intent(this, HttpService.class);
            startService(intent);
        });
        findViewById(R.id.btn_stop).setOnClickListener((v) -> {
            Intent intent = new Intent(this, HttpService.class);
            stopService(intent);
        });
    }
}
