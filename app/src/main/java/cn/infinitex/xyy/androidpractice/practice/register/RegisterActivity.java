package cn.infinitex.xyy.androidpractice.practice.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cn.infinitex.xyy.androidpractice.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText nameEt = findViewById(R.id.name);
        final Spinner genderSp = findViewById(R.id.gender);
        final EditText password1Et = findViewById(R.id.password);
        final EditText password2Et = findViewById(R.id.password2);
        final CheckBox box = findViewById(R.id.checkBox);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!box.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "请确认协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String name = nameEt.getText().toString();
                final int gender = genderSp.getSelectedItemPosition();
                final String password1 = password1Et.getText().toString();
                final String password2 = password2Et.getText().toString();

                if (!name.equals("") && !password1.equals("") && password1.equals(password2)) {
                    Person person = new Person(name, password1, gender);
                    Log.d(TAG, String.valueOf(person));
                    Intent intent = new Intent(RegisterActivity.this, OutputActivity.class);
                    intent.putExtra("person", person);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "输入信息有误", Toast.LENGTH_SHORT).show();
                    password2Et.setText("");
                }
            }
        });
    }
}
