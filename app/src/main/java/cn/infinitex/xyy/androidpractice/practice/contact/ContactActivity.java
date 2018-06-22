package cn.infinitex.xyy.androidpractice.practice.contact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.infinitex.xyy.androidpractice.R;

public class ContactActivity extends AppCompatActivity {
    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Email.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.DATA
    };
    ArrayAdapter<User> adapter;
    List<User> contactsList = new ArrayList<>(); // 重写User的toString方法实现仅显示名字

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ListView contactsView = findViewById(R.id.contacts_view);
        contactsView.setOnItemClickListener((parent, view, position, id) -> {
            showUserDetail(contactsList.get(position));
        });
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
        } else {
            readContacts();
        }
    }

    private void showUserDetail(User user) {
        AlertDialog alertDialog = new AlertDialog.Builder(ContactActivity.this).create();
        alertDialog.setTitle("详细内容");
        alertDialog.setMessage(String.format("姓名：%s\n电话：%s\n邮件：%s", user.name, user.phone, user.email));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "了解",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    private void readContacts() {
        Cursor cursor = null;
        Cursor cursorEmail = null;
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, null, null, null);
            cursorEmail = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, PROJECTION, null, null, null);
            if (cursor != null && cursorEmail != null) {
                while (cursor.moveToNext() && cursorEmail.moveToNext()) {
                    User user = new User();
                    String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String displayPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String displayEmail = cursorEmail.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    user.name = displayName;
                    user.phone = displayPhone;
                    user.email = displayEmail;
                    contactsList.add(user);
                }
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
