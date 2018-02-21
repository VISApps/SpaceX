package com.visapps.spacex.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.visapps.spacex.R;
import com.visapps.spacex.models.Launch;
import com.visapps.spacex.models.Launches;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class LaunchesAdapter extends RecyclerView.Adapter<LaunchesAdapter.ViewHolder>{

    private List<Launch> launches = new ArrayList<>();
    private Context context;
    private LaunchesAdapterCallback callback;

    public interface LaunchesAdapterCallback{
        void onOpenLink(String link);
        void onOpenTabs(String link);
    }

    public LaunchesAdapter(Context context){
        this.context = context;
    }

    public void setCallback(LaunchesAdapterCallback callback){
        this.callback = callback;
    }

    public void setLaunches(Launches launches){
        this.launches = launches.getLaunches();
        notifyDataSetChanged();
    }

    public void clearLaunches(){
        launches = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public LaunchesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.launches_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LaunchesAdapter.ViewHolder holder, int position) {
        Launch launch = launches.get(position);
        holder.rocket_name.setText(launch.getRocket().getRocketName());
        holder.launch_date.setText(getDate(launch.getLaunchDateUnix()));
        holder.launch_site.setText(launch.getLaunchSite().getSiteName());
        if(!launch.getDetails().equals("null")){
            holder.details.setText(launch.getDetails());
        }
        else{
            holder.details.setText(context.getString(R.string.nodetails));
        }
        Picasso.with(context).load(launch.getLinks().getMissionPatch()).placeholder(R.drawable.ic_image_gray_24dp).fit().centerCrop().into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return launches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView rocket_name,launch_date,launch_site, details;
        ImageView icon;
        ImageButton youtubebutton;
        View container;

        public ViewHolder(View itemView) {
            super(itemView);
            rocket_name = itemView.findViewById(R.id.rocket_name);
            launch_date = itemView.findViewById(R.id.launch_date);
            launch_site = itemView.findViewById(R.id.launch_site);
            details = itemView.findViewById(R.id.details);
            icon = itemView.findViewById(R.id.icon);
            container = itemView.findViewById(R.id.launchroot);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onOpenTabs(launches.get(getAdapterPosition()).getLinks().getArticleLink());
                }
            });
            youtubebutton = itemView.findViewById(R.id.youtubebutton);
            youtubebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onOpenLink(launches.get(getAdapterPosition()).getLinks().getVideoLink());
                }
            });
        }

    }

    private String getDate(Integer timestamp) {
        long time = Long.valueOf(timestamp);
        Date date = new Date(time*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        return sdf.format(date);
    }

}
