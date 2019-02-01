package com.example.bezpiecznedziecko;

import java.util.ArrayList;

public class DataHolder {

    private static DataHolder sSoleInstance;

    private DataHolder(){}  //private constructor.
    public static ArrayList<Notification> notifications;
    public static DataHolder getInstance(){
        if (sSoleInstance == null){ //if there is no instance available... create new one
            sSoleInstance = new DataHolder();
            notifications = new ArrayList<>();
        }
        return sSoleInstance;
    }
}
