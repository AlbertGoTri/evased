package edu.url.salle.albert.gt.evased.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import edu.url.salle.albert.gt.evased.R;
import edu.url.salle.albert.gt.evased.entities.Location;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationHolder> {
    private List<Location> mLocation;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public LocationsAdapter(Context context, List<Location> locations) {
        this.mInflater = LayoutInflater.from(context);
        mLocation = locations;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.event_locations_layout, parent, false);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        Location location = mLocation.get(position);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return mLocation.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Location mLocation;
        private ImageView imageView;
        private TextView mDescription;

        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.location_button);
            mDescription = (TextView) itemView.findViewById(R.id.location_information);
            itemView.setOnClickListener(this);
        }

        public void bind(Location location) {




            mLocation = location;

            mDescription.setText(mLocation.getLocation_description());
            Picasso.get().load(location.getLocation_image()).into(imageView);


        }
        @Override
        public void onClick(View view) {
            if(mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
