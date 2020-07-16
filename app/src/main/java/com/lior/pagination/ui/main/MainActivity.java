package com.lior.pagination.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lior.pagination.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}