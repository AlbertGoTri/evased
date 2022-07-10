package edu.url.salle.albert.gt.evased.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

import edu.url.salle.albert.gt.evased.R;
import edu.url.salle.albert.gt.evased.Search_users;
import edu.url.salle.albert.gt.evased.entities.User;

public class MyRecyclerViewRequestsAdapter extends RecyclerView.Adapter<MyRecyclerViewRequestsAdapter.UserHolder>{

    private ArrayList<User> mUsers;
    private ArrayList<User> mUsersOriginal;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    public MyRecyclerViewRequestsAdapter(Context context, ArrayList<User> users) {
        this.mInflater = LayoutInflater.from(context);
        this.mUsersOriginal = new ArrayList<>();
        this.mUsersOriginal.addAll(users);
        mUsers = users;

    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.friendrequest, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User userr = mUsers.get(position);

        holder.bind(userr);
    }

    public void filtrado(String txtBuscar ){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            mUsers.clear();
            mUsers.addAll(mUsersOriginal);
        }
        else {
            ArrayList<User> collection = (ArrayList<User>) mUsers.stream().filter(i -> i.getName().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            mUsers.clear();
            mUsers.addAll(collection);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    // View Holder
    public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private User mUs;
        private TextView mUserTextView;



        public UserHolder(View itemView) {
            super(itemView);
            mUserTextView = (TextView) itemView.findViewById(R.id.UserToAccept);
            //mSendMessageButton = (Button) itemView.findViewById(R.id.request_friendship_button);
            itemView.setOnClickListener(this);

        }

        public void bind(User user_) {

            mUs = user_;
            mUserTextView.setText(mUs.getName());

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    // convenience method for getting data at click position
    public User getItem(int id) {
        return mUsers.get(id);
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
