package com.example.habittracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditHabitActivity extends AppCompatActivity {

    private EditText habitNameEditText, habitDescriptionEditText, targetDaysEditText;
    private Button updateHabitButton, deleteHabitButton,backButton;
    private DatabaseHelper databaseHelper;
    private Habit habit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        habitNameEditText = findViewById(R.id.habitNameEditText);
        habitDescriptionEditText = findViewById(R.id.habitDescriptionEditText);
        targetDaysEditText = findViewById(R.id.targetDaysEditText);
        updateHabitButton = findViewById(R.id.updateHabitButton);
        deleteHabitButton = findViewById(R.id.deleteHabitButton);
        backButton = findViewById(R.id.backButton);

        databaseHelper = new DatabaseHelper(this);

        // Get habit ID from Intent and load the habit
        int habitId = getIntent().getIntExtra("habit_id", -1);
        habit = getHabitById(habitId);
        if (habit != null) {
            habitNameEditText.setText(habit.getName());
            habitDescriptionEditText.setText(habit.getDescription());
            targetDaysEditText.setText(String.valueOf(habit.getTargetDays()));
        }

        updateHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habit.setName(habitNameEditText.getText().toString().trim());
                habit.setDescription(habitDescriptionEditText.getText().toString().trim());
                habit.setTargetDays(Integer.parseInt(targetDaysEditText.getText().toString().trim()));

                databaseHelper.updateHabit(habit);
                Toast.makeText(EditHabitActivity.this, "Habit updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        deleteHabitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteHabit(habit.getId());
                Toast.makeText(EditHabitActivity.this, "Habit deleted successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditHabitActivity.this, MainActivity.class));
            }
        });
    }

    private Habit getHabitById(int habitId) {
        for (Habit h : databaseHelper.getAllHabits()) {
            if (h.getId() == habitId) {
                return h;
            }
        }
        return null;
    }
}
