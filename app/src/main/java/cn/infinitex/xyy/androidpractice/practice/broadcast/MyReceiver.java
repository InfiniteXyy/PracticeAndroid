package cn.infinitex.xyy.androidpractice.practice.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import cn.infinitex.xyy.androidpractice.R;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, ReceiverActivity.class);
        intent1.putExtra("number", intent.getIntExtra("number", 0));
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent1, 0);
        Notification notification = new NotificationCompat.Builder(context, "simple")
                .setContentTitle("广播标题")
                .setContentText("启动另一个活动")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher_foreground))
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }
}
