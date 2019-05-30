package com.example.notification;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class all_user_adapter extends RecyclerView.Adapter<all_user_adapter.ViewHolder> {

    private List<all_user> itemList;
    private Context context;

    public all_user_adapter(List<all_user> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_user,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        all_user ne=itemList.get(i);
        viewHolder.name.setText(ne.getName());
        Glide.with(context).load(ne.getImage()).into(viewHolder.image);
        final String user_id=ne.userId;
        final String name=ne.getName();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent=new Intent(context,sendActivity.class);
                    intent.putExtra("UserId",user_id);
                    intent.putExtra("name",name);
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
                }
                else{
                    Intent intent=new Intent(context,sendActivity.class);
                    intent.putExtra("UserId",user_id);
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView image;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.single_name);
            image=(CircleImageView)itemView.findViewById(R.id.single_image);
        }
    }

}
