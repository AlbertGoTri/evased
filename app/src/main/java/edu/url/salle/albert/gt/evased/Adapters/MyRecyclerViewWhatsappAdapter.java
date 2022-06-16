package edu.url.salle.albert.gt.evased.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.R;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.Message;
import edu.url.salle.albert.gt.evased.entities.User;

public class MyRecyclerViewWhatsappAdapter extends RecyclerView.Adapter<MyRecyclerViewWhatsappAdapter.MessageHolder>{

    private ArrayList<Message> mMessages;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private User actualUser;
    private User otherUser;


    public MyRecyclerViewWhatsappAdapter(Context context, Conversation conversation, User actualUser, User other) {
        this.mInflater = LayoutInflater.from(context);
        mMessages = conversation.getMessages();
        this.actualUser = actualUser;
        this.otherUser = other;
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.message_like_whatsapp, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        Message mess = mMessages.get(position);
        holder.bind(mess);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }


    // View Holder
    public class MessageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Message mMess;
        private TextView mUserTextView;
        private TextView mDateTextView;
        private TextView mMessageView;
        private LinearLayout mLinearLayout;

        private String separator = ": ";

        public MessageHolder(View itemView) {
            super(itemView);
            mUserTextView = (TextView) itemView.findViewById(R.id.message_name_whatsapp);
            mDateTextView = (TextView) itemView.findViewById(R.id.message_date_whatsapp);
            mMessageView = (TextView) itemView.findViewById(R.id.message_content_whatsapp);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout_message);
            itemView.setOnClickListener(this);

        }

        public void bind(Message mess_) {

            mMess = mess_;
            if(actualUser.getName().equals(mMess.getSender().getName())){
                mUserTextView.setText("You: ");
            }
            else{
                mUserTextView.setText(otherUser.getName() + separator);
            }

            mDateTextView.setText(mMess.getDate());

            mMessageView.setText(mMess.getContent());

            if(mMess.getSender().getName().equals(actualUser.getName())){
                mLinearLayout.setBackgroundColor(Color.BLUE);
                System.out.println("blue message: ---------------------- " +mMess.getContent());

            }
        }


        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    public Message getItem(int id) {
        return mMessages.get(id);
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
