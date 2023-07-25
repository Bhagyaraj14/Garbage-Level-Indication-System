package com.example.garbagemonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class NewUserActivity extends AppCompatActivity {

    private String fname, lname, emailid, phnum, uname, pwd, cpwd, userId;
    private EditText txtFname, txtLname, txtEmailId, txtPhnum, txtUname, txtPwd, txtCpwd;
    private Button btnNewUser, btnLogin;

    //Accept Only Alphabet in EditText
    public static InputFilter acceptonlyAlphabetValuesnotNumbersMethod() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                boolean isCheck = true;
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (isCharAllowed(c)) {
                        sb.append(c);
                    } else {
                        isCheck = false;
                    }
                }
                if (isCheck)
                    return null;
                else {
                    if (source instanceof Spanned) {
                        SpannableString spannableString = new SpannableString(sb);
                        TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, spannableString, 0);
                        return spannableString;
                    } else {
                        return sb;
                    }
                }
            }

            private boolean isCharAllowed(char c) {
                Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
                Matcher match = pattern.matcher(String.valueOf(c));
                return match.matches();
            }
        };
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        txtEmailId = (EditText) findViewById(R.id.editTextEmail);
        txtFname = (EditText) findViewById(R.id.editTextFname);
        txtLname = (EditText) findViewById(R.id.editTextLname);
        txtPwd = (EditText) findViewById(R.id.editTextPwd);
        txtCpwd = (EditText) findViewById(R.id.editTextConfirmPwd);
        txtUname = (EditText) findViewById(R.id.editTextUname);
        txtPhnum = (EditText) findViewById(R.id.editTextPhoneNum);
        btnNewUser=(Button) findViewById(R.id.SignUpBtn);
        btnLogin=(Button) findViewById(R.id.loginBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StaffLoginActivity.class);
                startActivity(intent);
            }
        });

        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = txtFname.getText().toString();
                lname = txtLname.getText().toString();
                emailid = txtEmailId.getText().toString();
                phnum = txtPhnum.getText().toString();
                uname = txtUname.getText().toString();
                pwd = txtPwd.getText().toString();
                cpwd = txtCpwd.getText().toString();

                Pattern pattern = Pattern.compile("^\\d{10}$");
                Matcher matcher = pattern.matcher(phnum);

                txtFname.setFilters(new InputFilter[]{acceptonlyAlphabetValuesnotNumbersMethod()});
                txtLname.setFilters(new InputFilter[]{acceptonlyAlphabetValuesnotNumbersMethod()});

                if(TextUtils.isEmpty(fname))
                {
                    Toast.makeText(getApplicationContext(),"First Name is Empty", Toast.LENGTH_LONG).show();
                    txtFname.setFocusable(true);
                }
                else if(TextUtils.isEmpty(lname))
                {
                    Toast.makeText(getApplicationContext(),"Last Name is Empty", Toast.LENGTH_LONG).show();
                    txtLname.setFocusable(true);
                }
                else if(TextUtils.isEmpty(emailid))
                {
                    Toast.makeText(getApplicationContext(),"EmailId is Empty", Toast.LENGTH_LONG).show();
                    txtEmailId.setFocusable(true);
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches())
                {
                    txtEmailId.setError("EmailId is Not Valid");
                    txtEmailId.setFocusable(true);
                }
                else if(TextUtils.isEmpty(phnum))
                {
                    Toast.makeText(getApplicationContext(),"Phnum is Empty", Toast.LENGTH_LONG).show();
                    txtPhnum.setFocusable(true);
                }
                else if(!matcher.matches())
                {
                    txtPhnum.setError("Phone Num is Invalid should be 10 digits");
                    txtPhnum.setFocusable(true);
                }
                else if(TextUtils.isEmpty(uname))
                {
                    Toast.makeText(getApplicationContext(),"UserName is Empty", Toast.LENGTH_LONG).show();
                    txtUname.setFocusable(true);
                }
                else if(TextUtils.isEmpty(pwd))
                {
                    Toast.makeText(getApplicationContext(),"Password is Empty", Toast.LENGTH_LONG).show();
                    txtPwd.setFocusable(true);
                }
                else if(TextUtils.isEmpty(cpwd))
                {
                    Toast.makeText(getApplicationContext(),"ConfirmPwd is Empty", Toast.LENGTH_LONG).show();
                    txtCpwd.setFocusable(true);
                }
                else if(!pwd.equals(cpwd))
                {
                    Toast.makeText(getApplicationContext(),"Password & ConfirmPwd is not equal", Toast.LENGTH_LONG).show();
                    txtCpwd.setFocusable(true);
                }
                else{
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewUser");
                    userId = dbRef.push().getKey();
                    NewUserClass newUserClass =new NewUserClass(userId, fname, lname,
                            emailid, phnum, uname, pwd, 0);
                    dbRef.child(userId).setValue(newUserClass);
                    Toast.makeText(getApplicationContext(),"New User Created Success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}