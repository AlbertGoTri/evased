package edu.url.salle.albert.gt.evased.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.R;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Event;

public class MyRecyclerViewEvents_TimelineAdapter extends RecyclerView.Adapter<MyRecyclerViewEvents_TimelineAdapter.EventHolder>{

    private ArrayList<Event> mEvents;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public MyRecyclerViewEvents_TimelineAdapter(Context context, ArrayList<Event> events) {
        this.mInflater = LayoutInflater.from(context);
        mEvents = events;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.timeline, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        Event eve = mEvents.get(position);
        holder.bind(eve);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }


    // View Holder
    public class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Event mEvent;
        private TextView mEventNameView;
        private TextView mDateTextView;
        private TextView mDescriptionView;


        public EventHolder(View itemView) {
            super(itemView);
            mEventNameView = (TextView) itemView.findViewById(R.id.Event_Name_Task_23);
            //mDateTextView = (TextView) itemView.findViewById(R.id.Event_end_date_23);
            mDescriptionView = (TextView) itemView.findViewById(R.id.description_event_23);
            itemView.setOnClickListener(this);

        }

        public void bind(Event event_) {
            mEvent = event_;
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

            mEventNameView.setText(mEvent.getName());
            //mDateTextView.setText(formatter.format(mEvent.getEnd_date()) );
            mDescriptionView.setText( mEvent.getDescription());
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    public Event getItem(int id) {
        return mEvents.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
