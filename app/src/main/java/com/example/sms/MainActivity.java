package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.WindowManager;
import android.widget.*;
import android.view.View;
import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    EditText et_phone,et_message;
    Button btn;
    PendingIntent sentPI,deliveredPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        et_phone=(EditText)findViewById(R.id.et_phone) ;
        et_message=(EditText)findViewById(R.id.et_message) ;
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=et_message.getText().toString();
                String tel=et_phone.getText().toString();
                if(tel.length()==10)
                {
                    if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
                        Toast.makeText(getApplicationContext(),"sms not sent",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(tel,null,message,sentPI,deliveredPI);
                        Toast.makeText(getApplicationContext(),"sms sent",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"sms not sent",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"enter 10 digit number",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}