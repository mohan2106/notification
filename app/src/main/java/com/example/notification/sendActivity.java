package com.example.notification;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class sendActivity extends AppCompatActivity {
    private TextView iduser;
    private EditText message;
    private ProgressBar pb2;
    private Button send;
    private FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    private String muserAuth;
    //private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        message=(EditText)findViewById(R.id.editTextnotification);
        send=(Button)findViewById(R.id.buttonsend);
        iduser=(TextView)findViewById(R.id.user_id_view);
        pb2=(ProgressBar)findViewById(R.id.progressBar2);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("UserId");
        final String name=intent.getStringExtra("name");
        muserAuth=FirebaseAuth.getInstance().getUid();
        iduser.setText("send notification to "+name);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mess=message.getText().toString();
                if(!TextUtils.isEmpty(mess)){
                    pb2.setVisibility(View.VISIBLE);
                    Map<String,Object> data=new HashMap<>();
                    data.put("meassage",mess);
                    data.put("User",muserAuth);
                    firestore.collection("Users").document(id).collection("Notification").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            pb2.setVisibility(View.GONE);
                            message.setText("");
                            Toast.makeText(sendActivity.this, "message saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pb2.setVisibility(View.GONE);
                            Toast.makeText(sendActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(sendActivity.this, "Please provide some message", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
