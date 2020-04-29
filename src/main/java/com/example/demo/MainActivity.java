package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, pass;
    Button clk;
    DatabaseHelper myDb;
    public final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        clk = (Button) findViewById(R.id.button8);
        myDb = new DatabaseHelper(this);

    }
    /*Login Page*/
    public void movepage(View view) {
        String stname = name.getText().toString();
        String stpass = pass.getText().toString();
        if (stname.equals("Raunak") && stpass.equals("raw")) {
            Intent in = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(in);
        } else if (stname.equals("") || stpass.equals("")) {
            Toast.makeText(getBaseContext(), "Enter name and password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Wrong name and password entered", Toast.LENGTH_SHORT).show();
        }


    }
    /*Register with Notification*/

    public void movepage2(View view) {
        Button button;
        Intent in = new Intent(MainActivity.this, Main3Activity.class);
        startActivity(in);
        button =(Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.stat_notify_error)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentTitle("ShadowFax")
                        .setContentText("Welcome to Shadowfax Family");
                notificationBuilder.setDefaults(
                        Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE
                );
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(1, notificationBuilder.build());


            }
        });

    }
}




