package com.example.habittracker;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddHabitActivity extends AppCompatActivity {

    private EditText habitNameEditText, habitDescriptionEditText, targetDaysEditText;
    private Button saveHabitButton,backButton;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        habitNameEditText = findViewById(R.id.habitNameEditText);
        habitDescriptionEditText = findViewById(R.id.habitDescriptionEditText);
        targetDaysEditText = findViewById(R.id.targetDaysEditText);
        saveHabitButton = findViewById(R.id.saveHabitButton);
        backButton = findViewById(R.id.backButton);

        databaseHelper = new DatabaseHelper(this);

        saveHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = habitNameEditText.getText().toString().trim();
                String description = habitDescriptionEditText.getText().toString().trim();
                String targetDaysStr = targetDaysEditText.getText().toString().trim();

                if (name.isEmpty() || targetDaysStr.isEmpty()) {
                    Toast.makeText(AddHabitActivity.this, "Please enter all required fields", Toast.LENGTH_SHORT).show();
                } else {
                    int targetDays = Integer.parseInt(targetDaysStr);
                    Habit habit = new Habit(0, name, description, targetDays);
                    databaseHelper.addHabit(habit);
                    Toast.makeText(AddHabitActivity.this, "Habit added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddHabitActivity.this, MainActivity.class));
            }
        });
    }
}
