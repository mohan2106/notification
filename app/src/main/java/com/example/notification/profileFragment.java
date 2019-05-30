package com.example.notification;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileFragment extends Fragment {

    private Button log_out;
    private FirebaseAuth mAuth;
    private TextView name;
    private CircleImageView image;
    private FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    private String uid;

    public profileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        log_out=(Button)v.findViewById(R.id.log_outbutton);
        mAuth=FirebaseAuth.getInstance();
        name=(TextView)v.findViewById(R.id.profile_name);
        uid=mAuth.getCurrentUser().getUid();
        image=(CircleImageView)v.findViewById(R.id.profileLabel_image);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser()!=null){
                    Map<String,Object> data=new HashMap<>();
                    data.put("token_id", FieldValue.delete());
                    firestore.collection("Users").document(uid).update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            mAuth.signOut();
                            Intent intent=new Intent(container.getContext(),LoginActivity.class);
                            startActivity(intent);
                            (MainActivity.ma).finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(container.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        firestore.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String username=documentSnapshot.getString("name");
                String userimage=documentSnapshot.getString("image");
                name.setText(username);
                Glide.with(container.getContext()).load(userimage).into(image);
            }
        });

        return v;
    }

}
