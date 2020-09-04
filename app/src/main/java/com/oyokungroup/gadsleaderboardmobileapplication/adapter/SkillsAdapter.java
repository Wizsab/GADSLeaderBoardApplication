package com.oyokungroup.gadsleaderboardmobileapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oyokungroup.gadsleaderboardmobileapplication.R;
import com.oyokungroup.gadsleaderboardmobileapplication.model.SkillResponse;
import com.squareup.picasso.Picasso;


import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.MyViewHolder> {

    private Context mContext;
    private List<SkillResponse> list;

    public SkillsAdapter(Context mContext, List<SkillResponse> List){
        this.mContext = mContext;
        this.list = List;
    }

    @Override
   public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.skill_card, viewGroup, false);
        return new MyViewHolder(view);
    }

     @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i){
         viewHolder.name.setText(list.get(i).getName());
         viewHolder.score.setText(String.valueOf(list.get(i).getScore()));
         viewHolder.country.setText(list.get(i).getCountry());

         Picasso.with(mContext)
                 .load(list.get(i).getBadgeUrl())
                 .fit()
                 .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbnail;
        public TextView name;
        public TextView score;
        public TextView country;
        public MyViewHolder(View view){
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.imageSkill);
            name = (TextView) view.findViewById(R.id.tvName);
            score = (TextView) view.findViewById(R.id.tvScore);
            country = (TextView) view.findViewById(R.id.tvCountry);
        }
    }
}
