package edu.url.salle.albert.gt.evased.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.url.salle.albert.gt.evased.R;
import edu.url.salle.albert.gt.evased.entities.Conversation;
import edu.url.salle.albert.gt.evased.entities.User;

public class MyRecyclerViewMessagesAdapter extends RecyclerView.Adapter<MyRecyclerViewMessagesAdapter.ConversationHolder>{

    private ArrayList<Conversation> mConversations;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private User actualUser;

    public MyRecyclerViewMessagesAdapter(Context context, ArrayList<Conversation> conversations, User actualUser) {
        this.mInflater = LayoutInflater.from(context);
        mConversations = conversations;
        this.actualUser = actualUser;

    }

    @Override
    public ConversationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.single_conversation_viewer, parent, false);
        return new ConversationHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationHolder holder, int position) {

        Conversation conv = mConversations.get(position);

        holder.bind(conv);
    }

    @Override
    public int getItemCount() {
        return mConversations.size();
    }


    // View Holder
    public class ConversationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Conversation mConv;
        private TextView mUserTextView;
        private TextView mDateTextView;
        private TextView mLastMessageView;

        private String youSending = "You: ";

        public ConversationHolder(View itemView) {
            super(itemView);
            mUserTextView = (TextView) itemView.findViewById(R.id.Name_User);
            mDateTextView = (TextView) itemView.findViewById(R.id.date_message);
            mLastMessageView = (TextView) itemView.findViewById(R.id.message_content);
            itemView.setOnClickListener(this);

        }

        public void bind(Conversation conv_) {

            mConv = conv_;

            mUserTextView.setText(mConv.getName());


            //mDateTextView.setText(mConv.getLastMessage().getTimeStamp());



            /*
            if(mConv.getLastMessage().getUser_id_sent() == (actualUser.getUserID())){
                String setextt = youSending + mConv.getLastMessage().getContent();
                //TODO CMABIAR NOI
                //mLastMessageView.setText(setextt);
                mLastMessageView.setText(setextt);

            }else{
                //TODO CMABIAR NOI
                //mLastMessageView.setText(mConv.getLastMessage().getContent());
                mLastMessageView.setText(mConv.getLastMessage().getContent());
            }


             */



        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    public Conversation getItem(int id) {
        return mConversations.get(id);
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
