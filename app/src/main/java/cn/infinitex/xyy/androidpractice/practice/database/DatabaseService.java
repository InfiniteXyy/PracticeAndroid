package cn.infinitex.xyy.androidpractice.practice.database;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DatabaseService extends Service {
    private Timer timer = new Timer();
    private MyDatabaseHelper dbHelper;

    public DatabaseService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        dbHelper = new MyDatabaseHelper(this, "Log.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Handler handler = new Handler(Looper.getMainLooper());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String time = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(new Date());
                String log = "日志：" + DateFormat.getTimeInstance().format(new Date());
                db.execSQL(String.format("insert into Log (create_time, log) values ('%s', '%s')", time, log));

                handler.post(() -> Toast.makeText(DatabaseService.this.getApplicationContext(),
                        "插入" + log, Toast.LENGTH_SHORT).show());
            }
        }, 0, 4000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(DatabaseService.this.getApplicationContext(),
                "关闭服务",
                Toast.LENGTH_SHORT).show());
        super.onDestroy();
    }
}
