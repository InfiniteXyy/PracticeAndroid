package cn.infinitex.xyy.androidpractice.practice.log;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import cn.infinitex.xyy.androidpractice.R;
import cn.infinitex.xyy.androidpractice.practice.database.MyDatabaseHelper;

public class LogDatabaseActivity extends AppCompatActivity {
    private List<Log> logs;
    private ArrayAdapter<Log> adapter;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_database);
        ListView listView = findViewById(R.id.listview);
        loadDB();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, logs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            showDbDetail(logs.get(position));
        });
    }

    private void showDbDetail(Log log) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("消息记录");
        alertDialog.setMessage("是否删除（" + log.log + "）");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "删除",
                (dialog, which) -> {
                    dbHelper.getWritableDatabase()
                            .delete("Log", "id = ?", new String[]{String.valueOf(log.id)});
                    adapter.remove(log);
                    dialog.dismiss();
                });
        alertDialog.show();
    }

    private void loadDB() {
        logs = new ArrayList<>();
        dbHelper = new MyDatabaseHelper(this, "Log.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long d = getToday();
        Cursor cursor = db.query("Log", null, "create_time > ?", new String[]{String.valueOf(d)}, null, null, "create_time desc");
        if (cursor.moveToNext()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                long time = cursor.getLong(cursor.getColumnIndex("create_time"));
                String log = cursor.getString(cursor.getColumnIndex("log"));
                logs.add(new Log(id, time, log));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    // 得到今天早上0点的时刻，（有更好的办法）
    private long getToday() {
        long d = new Date().getTime();
        int offset = TimeZone.getDefault().getOffset(d);
        d = ((d + offset) / 86400000l) * 86400000l - offset;
        return d;
    }
}
