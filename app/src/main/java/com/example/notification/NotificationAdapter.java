package com.example.notification;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<Notifications> itemList;
    private Context context;
    private FirebaseFirestore firestore=FirebaseFirestore.getInstance();

    public NotificationAdapter(List<Notifications> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_notification,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Notifications ne=itemList.get(i);
        String from_id=ne.getUser();
        viewHolder.message.setText(ne.getmeassage());
        firestore.collection("Users").document(from_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name=documentSnapshot.getString("name");
                String image=documentSnapshot.getString("image");

                viewHolder.notiname.setText(name);
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.mipmap.ic_launcher);
                Glide.with(context).setDefaultRequestOptions(requestOptions).load(image).into(viewHolder.notinotiimage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView notinotiimage;
        public TextView notiname;
        public TextView message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notinotiimage=(CircleImageView)itemView.findViewById(R.id.noti_image);
            notiname=(TextView)itemView.findViewById(R.id.noti_name);
            message=(TextView)itemView.findViewById(R.id.noti_data);

        }
    }
}
