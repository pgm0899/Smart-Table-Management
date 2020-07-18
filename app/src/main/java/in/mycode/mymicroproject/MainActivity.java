package in.mcode.mymicroproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           Toolbar toolbar=findViewById(R.id.toolbar);
           setSupportActionBar(toolbar);
           toolbar.setBackgroundColor(Color.parseColor("#000000"));
           getSupportActionBar().setTitle("Serve well!");

        Button order,vieworder,cancelorder,ordercompleted,paybill,exit;
        order=(Button)findViewById(R.id.button);
        vieworder=(Button)findViewById(R.id.button2);
        cancelorder=(Button)findViewById(R.id.button3);
        ordercompleted=(Button)findViewById(R.id.button4);
        paybill=(Button)findViewById(R.id.button5);
        exit=(Button)findViewById(R.id.button6);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity1.class);
                startActivity(intent);

                            }
        });

        vieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VieworderActivity.class);
                startActivity(intent);
            }
        });

        cancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CancelorderActivity.class);
                startActivity(intent);
            }
        });

        ordercompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrdercompletedActivity.class);
                startActivity(intent);

            }
        });
        paybill.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, PaybillActivity.class);
               startActivity(intent);
          }
           });
        exit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View arg0) {
                moveTaskToBack(true);
                finish();
            }
        });
    }
}
