package cn.infinitex.xyy.androidpractice.practice.subject;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.infinitex.xyy.androidpractice.R;

public class SubjectActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private List<String> description = Arrays.asList(
            "数学数学",
            "物理物理",
            "计算机计算机"
    );
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        drawerLayout = findViewById(R.id.drawer_layout);

        tv = findViewById(R.id.text);
        tv.setText(description.get(0));
        // 设置toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        // 设置侧滑栏
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_math);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_math:
                        tv.setText(description.get(0));
                        break;
                    case R.id.nav_physics:
                        tv.setText(description.get(1));
                        break;
                    case R.id.nav_computer:
                        tv.setText(description.get(2));
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        //设置 fragment (搁置)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }
}
