package cn.infinitex.xyy.androidpractice.practice.log;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import cn.infinitex.xyy.androidpractice.R;

public class LogDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_database);
        ListView listView = findViewById(R.id.listview);
    }
}
