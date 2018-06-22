package cn.infinitex.xyy.androidpractice.practice.broadcast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cn.infinitex.xyy.androidpractice.R;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
        TextView tv = findViewById(R.id.text);
        tv.setText(String.valueOf(getIntent().getIntExtra("number", 0)));
    }
}
