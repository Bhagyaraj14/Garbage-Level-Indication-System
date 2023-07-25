package com.example.garbagemonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
public class StaffLoginActivity extends AppCompatActivity {
    private Button loginBtn, gobackBtn, forgotpwdbtn;
    private FirebaseFirestore db;
    private String uname, pwd, userId;
    private Boolean flag;
    private EditText txtUname, txtPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        gobackBtn= (Button) findViewById(R.id.gobackBtn);

        txtUname = (EditText)findViewById(R.id.editTextUname);
        txtPwd = (EditText)findViewById(R.id.editTextPassword);
        db = FirebaseFirestore.getInstance();

        //txtUname.setText("bhagya");
        //txtPwd.setText("1234");

        gobackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NewUser");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=false;
                uname = txtUname.getText().toString();
                pwd  = txtPwd.getText().toString();

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
                else {
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String str = "";
                            for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                                NewUserClass userClass = SubSnapshot.getValue(NewUserClass.class);
                                if (userClass.getUserName().equals(uname) && userClass.getPassword().equals(pwd)) {
                                    userId = userClass.getUserId();
                                    Log.d("UserId ", userId);
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag) {
                                Toast.makeText(getApplicationContext(), "User Login Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), UserMainActivity.class);
                                intent.putExtra("userId", userId);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            System.out.println("Data Access Failed" + error.getMessage());
                        }
                    });
                }
                /*db.collection("NewUser").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                // after getting the data we are calling on success method
                                // and inside this method we are checking if the received
                                // query snapshot is empty or not.
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    // if the snapshot is not empty we are
                                    // hiding our progress bar and adding
                                    // our data in a list.
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    String userId = "";
                                    for (DocumentSnapshot d : list) {
                                        // after getting this list we are passing
                                        // that list to our object class.
                                        NewUserClass userClass = d.toObject(NewUserClass.class);
                                        if(userClass.getUserName().equals(uname) && userClass.getPassword().equals(pwd))
                                        {
                                            userId = userClass.getUserId();
                                            Log.d("UserId ", userId);
                                            flag=true;
                                            break;
                                        }
                                    }
                                    if(flag)
                                    {
                                        Toast.makeText(getApplicationContext(), "User Login Success", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), UserMainActivity.class);
                                        intent.putExtra("userId", userId);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // if the snapshot is empty we are displaying a toast message.
                                    Toast.makeText(getApplicationContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // if we do not get any data or any error we are displaying
                                // a toast message that we do not get any data
                                Toast.makeText(getApplicationContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                            }
                        });*/
            }
        });
    }
}