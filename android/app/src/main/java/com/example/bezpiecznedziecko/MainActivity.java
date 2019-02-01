package com.example.bezpiecznedziecko;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Token = ";
    private IntentFilter filter =  new IntentFilter(String.valueOf(R.string.BROADCAST_RECEIVER_COMMON));
    ListView notificationsListView;
    NotificationListViewAdapter notificationListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(broadcastReceiver, filter);
        notificationsListView =  findViewById(R.id.notificationsListView);
        notificationListViewAdapter = new NotificationListViewAdapter(this, R.layout.notifiacation, DataHolder.getInstance().notifications);
        notificationsListView.setAdapter(notificationListViewAdapter);
        printToken();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, filter);
        notificationListViewAdapter.notifyDataSetChanged();
    }

    private void printToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        Log.d(TAG, token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();
            String content = b.getString("content");
            Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
            notificationListViewAdapter.notifyDataSetChanged();
        }
    };
}
