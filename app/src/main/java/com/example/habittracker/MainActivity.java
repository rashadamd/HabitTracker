package com.example.habittracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HabitAdapter habitAdapter;
    private DatabaseHelper databaseHelper;
    private Button addHabitButton;
    private Button logoutButton;
    private SessionManager session;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());
        logoutButton = findViewById(R.id.logoutButton);
        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addHabitButton = findViewById(R.id.addHabitButton);
        addHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddHabitActivity.class));
            }
        });

        loadHabits();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void loadHabits() {
        List<Habit> habitList = databaseHelper.getAllHabits();
        habitAdapter = new HabitAdapter(habitList, this);
        recyclerView.setAdapter(habitAdapter);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadHabits();
    }
}