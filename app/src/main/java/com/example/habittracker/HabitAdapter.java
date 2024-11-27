package com.example.habittracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    private List<Habit> habitList;
    private Context context;

    public HabitAdapter(List<Habit> habitList, Context context) {
        this.habitList = habitList;
        this.context = context;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.habitNameTextView.setText(habit.getName());
        holder.habitDescriptionTextView.setText(habit.getDescription());
        holder.targetDaysTextView.setText("Target Days: " + habit.getTargetDays());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditHabitActivity.class);
                intent.putExtra("habit_id", habit.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView habitNameTextView, habitDescriptionTextView, targetDaysTextView;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            habitNameTextView = itemView.findViewById(R.id.habitNameTextView);
            habitDescriptionTextView = itemView.findViewById(R.id.habitDescriptionTextView);
            targetDaysTextView = itemView.findViewById(R.id.targetDaysTextView);
        }
    }
}
