package com.example.notification;

import android.app.Notification;

public class Notifications {
    String User,meassage;

    public Notifications(String user, String meassage) {
        User = user;
        this.meassage = meassage;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getmeassage() {
        return meassage;
    }

    public void setmeassage(String meassage) {
        this.meassage = meassage;
    }
}
