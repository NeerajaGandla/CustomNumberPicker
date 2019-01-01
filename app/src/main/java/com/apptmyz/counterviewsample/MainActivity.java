package com.apptmyz.counterviewsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.apptmyz.counterview.custom.CounterListener;
import com.apptmyz.counterview.custom.CounterView;

public class MainActivity extends AppCompatActivity {

    private CounterView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterView = (CounterView) findViewById(R.id.counterView);

        counterView.setCounterListener(new CounterListener() {
            @Override
            public void onIncrementClick(String value) {
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDecrementClick(String value) {
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

            }
        });

    }
}
