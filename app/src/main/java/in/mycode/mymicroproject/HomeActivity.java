package in.mcode.mymicroproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {
    private static int Splash_timeout= 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent firstintent=new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(firstintent);
                finish();
            }
        },Splash_timeout);
    }
}
