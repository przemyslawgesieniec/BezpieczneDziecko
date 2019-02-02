package com.example.bezpiecznedziecko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eadrwlo on 2018-05-20.
 */

public class NotificationListViewAdapter extends ArrayAdapter<Notification> {

    int layoutResourceId;
    Context mContext;
    ArrayList<Notification> notificationsArray = null;
    public NotificationListViewAdapter(Context context, int layoutResourceId, ArrayList<Notification> notificationsArray) {
        super(context, layoutResourceId, notificationsArray);
        this.layoutResourceId = layoutResourceId;
        this.notificationsArray = notificationsArray;
        this.mContext = context;
    }


    @Override
    public Notification getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View notification = inflater.inflate(layoutResourceId, parent, false);
        final Notification notificationObject = notificationsArray.get(position);
        TextView textView = notification.findViewById(R.id.notificationContent);
        textView.setText(notificationObject.content);

        ImageView statusIcon = notification.findViewById(R.id.icon);
        if (notificationObject.content.contains("entered"))
        {
            statusIcon.setImageResource(R.drawable.go_into_school);
        }
        else if (notificationObject.content.contains("left"))
        {
            statusIcon.setImageResource(R.drawable.go_out_from_school);
        }

        TextView notificationArriveTimeTextView = notification.findViewById(R.id.notificationArriveTime);

        notificationArriveTimeTextView.setText(notificationObject.sendTime);

        return notification;
    }

    private void removeNotification(Notification notification) {
        notificationsArray.remove(notification);
        notifyDataSetChanged();
    }


}
