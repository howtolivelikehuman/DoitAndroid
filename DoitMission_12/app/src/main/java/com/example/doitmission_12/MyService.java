package com.example.doitmission_12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate Called");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStratCommand Called");
        if(intent == null){
            return Service.START_STICKY;
        }else{
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String text = intent.getStringExtra("text");
        String name = intent.getStringExtra("name");


        Intent sendIntent = new Intent("com.example.doitmission12.myAction");
        sendIntent.putExtra("text", text);
        sendIntent.putExtra("name", name + "서비스 거침\n");
        sendBroadcast(sendIntent);
        Log.d(TAG, "서비스에서 받은 문자 : " + text);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
