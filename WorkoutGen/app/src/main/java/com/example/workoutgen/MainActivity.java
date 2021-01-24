package com.example.workoutgen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.logging.Logger;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWorkoutPage();
            }
        });
    }

    private void goToWorkoutPage() {
        Intent intent = new Intent(this, WorkoutPage.class);
        startActivity(intent);
    }
}
