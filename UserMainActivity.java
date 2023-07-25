package com.example.garbagemonitoringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class UserMainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView, iconimageView;
    private TextView textView;
    private FirebaseFirestore db;
    private String userid;
    private int distance;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        button = (Button) findViewById(R.id.gobackbtn);

        imageView = (ImageView) findViewById(R.id.imageView);
        iconimageView = (ImageView) findViewById(R.id.iconimageView);
        textView = (TextView) findViewById(R.id.txtView);

        Intent intent = getIntent();
        userid = intent.getStringExtra("userId");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("NewUser");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = "";
                for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                    NewUserClass userClass = SubSnapshot.getValue(NewUserClass.class);
                    if(userid.equals(userClass.getUserId()))
                    {
                        distance = userClass.getDistance();
                    }
                }

                if(distance<=15) {
                    imageView.setImageResource(R.drawable.fifteenpercentage1);
                    iconimageView.setImageResource(R.drawable.greenicon);
                    textView.setText("Garbage Filled : " + distance + "%");
                }
                else if(distance<=50) {
                    imageView.setImageResource(R.drawable.fiftypercentage1);
                    iconimageView.setImageResource(R.drawable.blueicon);
                    textView.setText("Garbage Filled : " + distance + "%");
                }
                else {
                    imageView.setImageResource(R.drawable.ninetyfivepercentage1);
                    iconimageView.setImageResource(R.drawable.redicon);
                    textView.setText("Garbage Filled : " + distance + "% Action Needed");
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Data Access Failed" + error.getMessage());
            }
        });


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
                            for (DocumentSnapshot d : list) {
                                NewUserClass userClass = d.toObject(NewUserClass.class);
                                if(userid.equals(userClass.getUserId()))
                                {
                                    distance=userClass.getDistance();
                                    break;
                                }
                            }
                            if(distance<=15)
                                imageView.setImageResource(R.drawable.fifteenpercentage1);
                            else if(distance<=50)
                                imageView.setImageResource(R.drawable.fiftypercentage1);
                            else
                                imageView.setImageResource(R.drawable.ninetyfivepercentage1);
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
}