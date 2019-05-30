package com.example.notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class notificationActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        tv=(TextView)findViewById(R.id.noti_text);
        Intent intent=getIntent();
        String mess=intent.getStringExtra("message");
        String from=intent.getStringExtra("from_id");
        tv.setText("FROM: "+from+" MESSAGE: "+mess);
    }
}
