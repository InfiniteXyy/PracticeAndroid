package cn.infinitex.xyy.androidpractice.practice.httpService;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class HttpService extends Service {
    private final static String MY_URL = "http://115.29.231.93:8080/CkeditorTest/AndroidTest?userId=10165101261&style=json";
    private ObjectMapper mapper = new ObjectMapper();
    private Timer timer = new Timer();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // 使用Handler在主线程中运行代码，使用Timer定时重复代码片段
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Handler handler = new Handler(Looper.getMainLooper());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ResponseEntity res = mapper.readValue(new URL(MY_URL), ResponseEntity.class);
                    StringBuilder toastText = new StringBuilder();
                    if (res.getReturnCode() < 0) {
                        toastText.append("学号：10165101261，回复：").append(res.getReturnValue());
                    } else if (res.getReturnCode() == 0) {
                        toastText.append(res.getReturnValue());
                    }
                    handler.post(() -> Toast.makeText(HttpService.this.getApplicationContext(),
                            toastText.toString(),
                            Toast.LENGTH_SHORT).show());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 4000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(HttpService.this.getApplicationContext(),
                "退出服务",
                Toast.LENGTH_SHORT).show());
        timer.cancel();
        super.onDestroy();
    }
}
