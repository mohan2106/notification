package com.example.notification;

import android.support.annotation.NonNull;

public class user_id {
    public String userId;
    public <T extends user_id> T withId(@NonNull final String id){
        this.userId=id;
        return (T) this;
    }
}
