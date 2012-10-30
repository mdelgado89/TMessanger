package com.ss.tmessanger;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class SmsThreadsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_threads);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sms_threads, menu);
        return true;
    }
}
