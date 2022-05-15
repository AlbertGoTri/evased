package edu.url.salle.albert.gt.evased.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.url.salle.albert.gt.evased.R;
import edu.url.salle.albert.gt.evased.entities.EventsTypes;

public class EventsTypeAdapter extends RecyclerView.Adapter<EventsTypeAdapter.EventsTypeHolder> {

    private List<EventsTypes> buttonList;
    private LayoutInflater mInflater;
    private EventsTypeAdapter.ItemClickListener mClickListener;

    public EventsTypeAdapter(Context context, ArrayList<EventsTypes> buttons) {
        this.mInflater = LayoutInflater.from(context);
        buttonList = buttons;
    }

    @NonNull
    @Override
    public EventsTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.horizontal_events_buttons_layout, parent, false);
        return new EventsTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsTypeHolder holder, int position) {
        EventsTypes eventsTypes = buttonList.get(position);
        holder.bind(eventsTypes);
    }

    @Override
    public int getItemCount() {
        return buttonList.size();
    }

    public class EventsTypeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private EventsTypes mEventsTypes;
        private Button button;
        private String name;

        public EventsTypeHolder(@NonNull View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.btn_event_type);
            itemView.setOnClickListener(this);
        }


        public void bind(EventsTypes eventsTypes) {
            mEventsTypes = eventsTypes;
            button.setText(mEventsTypes.getString());
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(EventsTypeAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
