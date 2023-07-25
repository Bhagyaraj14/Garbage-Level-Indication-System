package com.example.garbagemonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText txtUname, txtPwd;
    private Button loginBtn, exitBtn;
    private String uname, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        txtUname = (EditText) findViewById(R.id.editTextUname);
        txtPwd = (EditText) findViewById(R.id.editTextPassword);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        exitBtn = (Button) findViewById(R.id.gobackBtn);

        //txtUname.setText("admin");
        //txtPwd.setText("admin");

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = txtUname.getText().toString();
                pwd = txtPwd.getText().toString();

                if(TextUtils.isEmpty(uname))
                {
                 txtUname.setError("Login Name is Empty");
                    txtUname.setFocusable(true);
                }
                else if(TextUtils.isEmpty(pwd))
                {
                    txtPwd.setError("Password is Empty");
                    txtPwd.setFocusable(true);
                }
                else if(uname.equals("admin") && pwd.equals("admin")) {
                    Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}