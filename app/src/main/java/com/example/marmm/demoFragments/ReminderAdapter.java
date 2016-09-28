package com.example.marmm.demoFragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by marmm on 9/26/16.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private Cursor remindersCursor;


    public ReminderAdapter(Cursor cursor){

        remindersCursor = cursor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);

        }



}


    public ReminderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View reminderView = inflater.inflate(R.layout.row_reminder, parent, false);

        // Return a new holder instance
        ReminderAdapter.ViewHolder viewHolder = new ReminderAdapter.ViewHolder(reminderView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReminderAdapter.ViewHolder holder, int position) {
        TextView textView = holder.description;
        final String reminder;

        if (remindersCursor !=null && remindersCursor.moveToPosition(position))
        {
            reminder = (remindersCursor.getString(remindersCursor.getColumnIndex(DBhelper.REMINDER_NAME)));

            textView.setText(reminder);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra(MainActivity.REMINDER, reminder);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (remindersCursor == null ? 0 : remindersCursor.getCount());
    }
}
