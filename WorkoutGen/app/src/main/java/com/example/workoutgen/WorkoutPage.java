package com.example.workoutgen;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkoutPage extends AppCompatActivity {
        ArrayList<String> aList = new ArrayList<String>();
        ArrayList<String> rList = new ArrayList<String>();
        ArrayList<String> eList = new ArrayList<String>();
        ArrayList<Integer> drawablePics = new ArrayList<Integer>();
        CustomAdapter adapter;
        private ListView workoutList;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);
        DBHelper dbHelper = new DBHelper(this);
        try {
            viewData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String i: eList){
            if (i.equals("bar"))
                drawablePics.add(R.drawable.bar);
            else if (i.equals("bb"))
                drawablePics.add(R.drawable.barbell);
            else if (i.equals("db"))
                drawablePics.add(R.drawable.dumbells);
            else if (i.equals("bench"))
                drawablePics.add(R.drawable.benchbw);
            else if (i.equals("inc_bench"))
                drawablePics.add(R.drawable.inc_bench);
            else if (i.equals("plate"))
                drawablePics.add(R.drawable.plate);
            else if (i.equals("kb"))
                drawablePics.add(R.drawable.kettlebell);
            else if (i.equals("cb"))
                drawablePics.add(R.drawable.cb);
            else if (i.equals("floor"))
                drawablePics.add(R.drawable.floor);
            else{}
        }
        System.out.printf(String.valueOf(eList));
        System.out.printf("\nPIC SIZE: %d%n", drawablePics.size());
        System.out.printf("NAME SIZE: %d%n", aList.size());
        CustomAdapter adapter = new CustomAdapter(this, aList, rList, drawablePics);
        workoutList = (ListView)findViewById(R.id.activity_list);
        workoutList.setAdapter(adapter);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aList.clear();
                eList.clear();
                rList.clear();
                drawablePics.clear();
                finish();
                finishAffinity();
                reloadPage();
            }
        });
    }

    private void reloadPage() {
        Intent intent = new Intent(this, WorkoutPage.class);
        startActivity(intent);
    }
    private void viewData() throws SQLException {
        DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
        dbAdapter.open();
        Cursor cursor = dbAdapter.getTestData();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            aList.add(cursor.getString(cursor.getColumnIndex("activity_name"))); //index
            rList.add(cursor.getString(cursor.getColumnIndex("reps")));
            eList.add(cursor.getString(cursor.getColumnIndex("equipment")));
            cursor.moveToNext();
        }
        dbAdapter.close();
    }

}