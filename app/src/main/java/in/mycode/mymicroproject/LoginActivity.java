package in.mcode.mymicroproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edittext_email, edittext_password;
    Button button_login,button_signup;
    private ProgressDialog progress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edittext_email = findViewById(R.id.edittext_email);
        edittext_password = findViewById(R.id.edittext_password);
        button_login = findViewById(R.id.button_login);
        button_signup = findViewById(R.id.bt_signup);

        for(int i=1;i<=4;i++) {
            MySingleton.getInstance().getTableArrayList().add(i - 1, new Table(i));
        }
        MySingleton.getInstance().createMenuPrice();
    }

    public void onLoginButtonClick (View view){
        String email = edittext_email.getText().toString();
        String password = edittext_password.getText().toString();
        DBHelper db = new DBHelper(LoginActivity.this);
        String response = db.search(email, password);
        if(response.equals("Successfully logged in")){
            Toast.makeText(LoginActivity.this, "Logged in!" , Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Login failed" , Toast.LENGTH_SHORT).show();
        }
    }

    public void onSignupButtonClick(View view)
    {
        String email=edittext_email.getText().toString();
        String password=edittext_password.getText().toString();
        DBHelper db=new DBHelper(LoginActivity.this);
        String response = db.addUser(email,password);
        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
    }
}

