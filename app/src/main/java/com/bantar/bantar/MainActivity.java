package com.bantar.bantar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.bantar.bantar.activities.IcebreakersActivity;
import com.bantar.bantar.activities.JustAMinuteActivity;
import com.bantar.bantar.activities.ThisOrThatActivity;
import com.bantar.bantar.activities.WouldYouRatherActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttonQuestion;
    private Button buttonWYR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onIBClick(View v) {
        startActivity(new Intent(MainActivity.this, IcebreakersActivity.class));
    }

    public void onWYRClick(View v) {
        startActivity(new Intent(MainActivity.this, WouldYouRatherActivity.class));
    }

    public void onTOTClick(View v) {
        startActivity(new Intent(MainActivity.this, ThisOrThatActivity.class));
    }

    public void onJAMClick(View v) {
        startActivity(new Intent(MainActivity.this, JustAMinuteActivity.class));
    }

}
