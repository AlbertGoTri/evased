package edu.url.salle.albert.gt.evased.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.R;

public class EventsTypeAdapter extends RecyclerView.Adapter<EventsTypeAdapter.ViewHolder> {

    ArrayList<Button> buttons;

    public EventsTypeAdapter(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public Button btn_event_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_event_type = itemView.findViewById(R.id.btn_event_type);
        }
    }

    @NonNull
    @Override
    public EventsTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_events_screen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsTypeAdapter.ViewHolder holder, int position) {
        Button button = buttons.get(position);
        button.setText("we");
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }
}
